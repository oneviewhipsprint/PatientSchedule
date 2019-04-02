package com.davita.dps.patientschedule.service;

import com.davita.dps.patientschedule.kafka.producer.KafkaProducer;
import com.davita.dps.patientschedule.model.PatientScheduleMessage;
import com.davita.dps.patientschedule.model.PatientScheduleMessageType;
import com.davita.dps.patientschedule.model.Schedule;
import com.davita.dps.patientschedule.model.WaitList;
import com.davita.dps.patientschedule.repository.WaitListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WaitListService {

    private WaitListRepository waitListRepository;
    private KafkaProducer kafkaProducer;


    public WaitListService(WaitListRepository waitListRepository, KafkaProducer kafkaProducer) {
        this.waitListRepository = waitListRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public void notifyWaitListers(Schedule schedule) {
        List<WaitList> waitListers =
                waitListRepository.getWaitListByClinicIdAndShiftDateAndShiftIdAndChairIdOrderByEnteredDateTime(
                    schedule.getClinicId(), schedule.getShiftDate(),schedule.getShiftId(), schedule.getChairId());
        if (!waitListers.isEmpty()) {
            sendWaitListNotification(schedule, waitListers.get(0));
        }
    }

    public void sendWaitListNotification(Schedule schedule, WaitList waitLister) {
        PatientScheduleMessage message = PatientScheduleMessage.builder()
                .id(UUID.randomUUID())
                .messageType(PatientScheduleMessageType.WAITLIST.toString())
                .patientId(waitLister.getPatientId())
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
