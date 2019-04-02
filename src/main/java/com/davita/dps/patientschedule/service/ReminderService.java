package com.davita.dps.patientschedule.service;

import com.davita.dps.patientschedule.kafka.producer.KafkaProducer;
import com.davita.dps.patientschedule.model.PatientScheduleMessage;
import com.davita.dps.patientschedule.model.PatientScheduleMessageType;
import com.davita.dps.patientschedule.model.Schedule;
import com.davita.dps.patientschedule.repository.PatientClinicScheduleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Service
public class ReminderService {

    // how often we check to see if it's time to send reminders
    private static final int REMINDER_INTERVAL_MILLIS = 60 * 1000;  // 1 minute

    private KafkaProducer kafkaProducer;
    private PatientClinicScheduleRepository scheduleRepository;

    public ReminderService(KafkaProducer kafkaProducer,
                           PatientClinicScheduleRepository scheduleRepository) {
        this.kafkaProducer = kafkaProducer;
        this.scheduleRepository = scheduleRepository;
    }

    @PostConstruct
    public void init() {
        sendReminders();
    }

    @Async
    public void sendReminders() {
        boolean ok = true;

        while (ok) {

            // get all scheduled appointments whose reminder not already sent
            List<Schedule> appointments = scheduleRepository.findAllByReminderSent(false);
            if (!appointments.isEmpty()) {
                appointments.forEach(schedule -> {
                    sendReminder(schedule);

                    // update the schedule.reminderSent field
                    schedule.setReminderSent(true);
                    scheduleRepository.save(schedule);
                });
            }

            // sleep for the interval
            try { Thread.sleep(REMINDER_INTERVAL_MILLIS); }
            catch (InterruptedException ie) {
                ok = false;
            }
        }
    }

    private void sendReminder(Schedule schedule) {
        PatientScheduleMessage message = PatientScheduleMessage.builder()
                .id(UUID.randomUUID())
                .messageType(PatientScheduleMessageType.REMINDER.toString())
                .patientId(schedule.getPatientId())
                .clinicId(schedule.getClinicId())
                .shiftDate(schedule.getShiftDate())
                .shiftId(schedule.getShiftId())
                .chairId(schedule.getChairId()).build();

        ObjectMapper jsonObjectMapper = new ObjectMapper();
        String json = null;
        try {
            json = jsonObjectMapper.writeValueAsString(message);
        } catch (Exception e) {}

        kafkaProducer.sendMessage(json);
    }
}
