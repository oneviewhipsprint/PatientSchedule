package com.davita.dps.patientschedule.service;

import com.davita.dps.patientschedule.model.Schedule;
import com.davita.dps.patientschedule.model.WaitList;
import com.davita.dps.patientschedule.model.WaitListStatusType;
import com.davita.dps.patientschedule.repository.PatientClinicScheduleRepository;
import com.davita.dps.patientschedule.repository.WaitListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleService {
    private PatientClinicScheduleRepository patientClinicScheduleRepository;
    private WaitListRepository waitListRepository;
    private WaitListService waitListService;

    @Autowired
    public ScheduleService(PatientClinicScheduleRepository patientClinicScheduleRepository,
                           WaitListRepository waitListRepository,
                           WaitListService waitListService) {
        this.patientClinicScheduleRepository = patientClinicScheduleRepository;
        this.waitListRepository = waitListRepository;
        this.waitListService = waitListService;
    }

    public Schedule bookSchedule(Integer patientId, Schedule schedule) {
        schedule.setScheduleId(UUID.randomUUID());
        return patientClinicScheduleRepository.insert(schedule);
    }

    public void cancelSchedule(Integer patientId, UUID scheduleId) throws RuntimeException {
        Optional<Schedule> optionalSchedule = patientClinicScheduleRepository.findById(scheduleId);
        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            if (schedule.getPatientId().equals(patientId)) {
                patientClinicScheduleRepository.delete(optionalSchedule.get());
                waitListService.notifyWaitListers(schedule);
            } else {
                throw new RuntimeException("Schedule does not exist for this patient");
            }
        } else {
            throw new RuntimeException("Schedule does not exist");
        }
    }

    public WaitList addToWaitList(Integer patientId, WaitList waitList) throws RuntimeException {
        WaitList savedWaitList = null;
        Optional<Schedule> optionalSchedule = patientClinicScheduleRepository.findByClinicIdAndShiftDateAndChairIdAndShiftId(waitList.getClinicId(),
                waitList.getShiftDate(), waitList.getChairId(), waitList.getShiftId());

        if (optionalSchedule.isPresent()) {
            waitList.setId(UUID.randomUUID());
            waitList.setStatus("PENDING");
            savedWaitList = waitListRepository.insert(waitList);
        } else {
            throw new RuntimeException("No schedule exists for this clinic/shift/chair");
        }

        return savedWaitList;
    }

    public Schedule reschduleSchdeule(Integer patientId, UUID scheduleId, Schedule schedule) {
        cancelSchedule(patientId, scheduleId);
        return bookSchedule(patientId, schedule);
    }

    public List<Schedule> getSchedules(String date) {
        return patientClinicScheduleRepository.findAllByShiftDate(date);
    }
}
