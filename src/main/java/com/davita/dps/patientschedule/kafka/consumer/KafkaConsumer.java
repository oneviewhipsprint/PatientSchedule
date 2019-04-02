package com.davita.dps.patientschedule.kafka.consumer;

import com.davita.dps.patientschedule.model.PatientScheduleMessage;
import com.davita.dps.patientschedule.model.PatientScheduleMessageType;
import com.davita.dps.patientschedule.repository.PatientScheduleMessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    PatientScheduleMessageRepository repo;

    @KafkaListener(topics = "patient_schedule", groupId = "group_id")
    public void consume(String jsonMessage) throws Exception {
        PatientScheduleMessage psm;

        try {
            // flip the json message into a PatientScheduleMessage
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            psm = jsonObjectMapper.readValue(jsonMessage, PatientScheduleMessage.class);
        } catch (Exception e) {
            String errorMsg = "Received invalid json message [" + jsonMessage + "]. Error was: " + e.getMessage();
            log.error(errorMsg, e);
            throw new Exception(errorMsg);
        }

        // get the PatientScheduleMessageType
        PatientScheduleMessageType messageType = PatientScheduleMessageType.getTypeEnum(psm.getMessageType());
        if (messageType == null) {
            String errorMsg = "Received invalid message type [" + psm.getMessageType() + "]. " +
            "JSON Message was [" + jsonMessage + "].";
            log.error(errorMsg);
            throw new Exception(errorMsg);
        }

        if (messageType == PatientScheduleMessageType.BOOKING) {
        } else if (messageType == PatientScheduleMessageType.ADD_TO_WAITLIST) {
        } else if (messageType == PatientScheduleMessageType.CANCELLATION) {
        } else if (messageType == PatientScheduleMessageType.WAITLISTED_APPT_ACCEPTED) {
        } else if (messageType == PatientScheduleMessageType.WAITLISTED_APPT_DECLINED) {

        }

        // test save to db
//        psm.setId(UUID.randomUUID());
//        repo.save(psm);

        log.info(String.format("#### -> Consumed message -> %s", jsonMessage));
    }
}
