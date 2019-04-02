package com.davita.dps.patientschedule.service;

import com.davita.dps.patientschedule.kafka.producer.KafkaProducer;
import com.davita.dps.patientschedule.model.PatientScheduleMessage;
import com.davita.dps.patientschedule.model.PatientScheduleMessageType;
import com.davita.dps.patientschedule.model.Schedule;
import com.davita.dps.patientschedule.model.WaitList;
import com.davita.dps.patientschedule.model.WaitListStatusType;
import com.davita.dps.patientschedule.repository.PatientClinicScheduleRepository;
import com.davita.dps.patientschedule.repository.WaitListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableAsync
public class WaitListService {

    private static final int WAIT_TIME_MILLIS = 30 * 1000;  // 30 seconds

    private WaitListRepository waitListRepository;
    private KafkaProducer kafkaProducer;
    private PatientClinicScheduleRepository scheduleRepository;

    public WaitListService(WaitListRepository waitListRepository,
                           KafkaProducer kafkaProducer,
                           PatientClinicScheduleRepository scheduleRepository) {
        this.waitListRepository = waitListRepository;
        this.kafkaProducer = kafkaProducer;
        this.scheduleRepository = scheduleRepository;
    }

    @Async
    public void notifyWaitListers(Schedule schedule) {
        while (true) {
            WaitList nextPendingWaitlister = getNextPendingWaitlister(schedule);
            if (nextPendingWaitlister == null) {
                return; // no waitlisters
            }

            // update the waitlister's status to NOTIFIED
            nextPendingWaitlister.setStatus(WaitListStatusType.NOTIFIED.toString());
            waitListRepository.save(nextPendingWaitlister);

            // notify waitlister
            sendWaitListNotification(schedule, nextPendingWaitlister);

            // wait for accept or decline from waitlister
            try { Thread.sleep(WAIT_TIME_MILLIS); }
            catch (InterruptedException ie) {}

            // query the schedule to see if this opening was accepted
            Optional<Schedule> accepted = scheduleRepository.findByClinicIdAndShiftDateAndChairIdAndShiftId(
                    schedule.getClinicId(),
                    schedule.getShiftDate(),
                    schedule.getChairId(),
                    schedule.getShiftId());
            if (accepted.isPresent()) {
                return; // we're done!
            }
        }
    }

    private void sendWaitListNotification(Schedule schedule, WaitList waitLister) {
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

    private WaitList getNextPendingWaitlister(Schedule schedule) {
        List<WaitList> waitListers =
                waitListRepository.getWaitListByClinicIdAndShiftDateAndShiftIdAndChairIdAndStatus(
                        schedule.getClinicId(),
                        schedule.getShiftDate(),
                        schedule.getShiftId(),
                        schedule.getChairId(),
                        WaitListStatusType.PENDING.toString());
        if (!waitListers.isEmpty()) {
            List<WaitList> sortedList = waitListers.stream().sorted(Comparator.comparing((WaitList::getEnteredDateTime))).collect(Collectors.toList());
            // always return first one in list
            return sortedList.get(0);
        }
        return null;
    }
}
