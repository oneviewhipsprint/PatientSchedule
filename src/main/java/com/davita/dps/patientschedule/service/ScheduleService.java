package com.davita.dps.patientschedule.service;

import com.davita.dps.patientschedule.model.Schedule;
import com.davita.dps.patientschedule.repository.PatientClinicScheduleRepository;
import com.davita.dps.patientschedule.repository.WaitListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleService {
    private PatientClinicScheduleRepository scheduleRepository;
    private WaitListRepository waitListRepository;
    private WaitListService waitListService;

    @Autowired
    public ScheduleService(PatientClinicScheduleRepository scheduleRepository,
                           WaitListRepository waitListRepository,
                           WaitListService waitListService) {
        this.scheduleRepository = scheduleRepository;
        this.waitListRepository = waitListRepository;
        this.waitListService = waitListService;
    }

    public Schedule bookSchedule(Integer patientId, Schedule schedule) {
        Schedule savedSchedule = null;
        Optional<Schedule> optionalSchedule = scheduleRepository.findByClinicIdAndShiftDateAndChairIdAndShiftId(schedule.getClinicId(), schedule.getShiftDate(), schedule.getChairId(), schedule.getShiftId());

        if (optionalSchedule.isPresent()) {
            throw new RuntimeException("Schedule already exists for this clinic/date/shift/chair");
        } else {
            schedule.setScheduleId(UUID.randomUUID());
            savedSchedule = scheduleRepository.insert(schedule);
        }

        return savedSchedule;
    }

    public void cancelSchedule(Integer patientId, UUID scheduleId) throws RuntimeException {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            if (schedule.getPatientId().equals(patientId)) {
                scheduleRepository.delete(optionalSchedule.get());
                waitListService.notifyWaitListers(schedule);
            } else {
                throw new RuntimeException("Schedule does not exist for this patient");
            }
        } else {
            throw new RuntimeException("Schedule does not exist");
        }
    }

    public Schedule reschduleSchdeule(Integer patientId, UUID scheduleId, Schedule schedule) {
        cancelSchedule(patientId, scheduleId);
        return bookSchedule(patientId, schedule);
    }

    public List<Schedule> getSchedules(String date) {
        return scheduleRepository.findAllByShiftDate(date);
    }
}
