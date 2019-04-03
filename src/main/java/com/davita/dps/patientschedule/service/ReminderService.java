package com.davita.dps.patientschedule.service;

import com.davita.dps.patientschedule.kafka.producer.KafkaProducer;
import com.davita.dps.patientschedule.model.PatientScheduleMessage;
import com.davita.dps.patientschedule.model.PatientScheduleMessageType;
import com.davita.dps.patientschedule.model.Schedule;
import com.davita.dps.patientschedule.repository.PatientClinicScheduleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {

    // how often we check to see if it's time to send reminders
    private static final int REMINDER_INTERVAL_MILLIS = 30 * 1000;  // 30 seconds

    private KafkaProducer kafkaProducer;
    private PatientClinicScheduleRepository scheduleRepository;

    public ReminderService(KafkaProducer kafkaProducer,
                           PatientClinicScheduleRepository scheduleRepository) {
        this.kafkaProducer = kafkaProducer;
        this.scheduleRepository = scheduleRepository;
    }

    @Async
    public void sendReminder(Schedule schedule) {
        // sleep for the interval
        try {
            Thread.sleep(REMINDER_INTERVAL_MILLIS);
        } catch (InterruptedException ie) {}

        PatientScheduleMessage message = PatientScheduleMessage.builder()
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
        } catch (Exception e) { }

        kafkaProducer.sendMessage(json);

        // update reminderSent flag in schedule
        schedule.setReminderSent(true);
        scheduleRepository.save(schedule);
    }
}