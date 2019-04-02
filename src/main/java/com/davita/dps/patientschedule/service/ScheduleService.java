package com.davita.dps.patientschedule.service;

import com.davita.dps.patientschedule.model.Schedule;
import com.davita.dps.patientschedule.model.WaitList;
import com.davita.dps.patientschedule.repository.ClinicRepository;
import com.davita.dps.patientschedule.repository.PatientClinicScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleService {
    private ClinicRepository clinicRepository;
    private PatientClinicScheduleRepository patientClinicScheduleRepository;
    private WaitListService waitListService;

    @Autowired
    public ScheduleService(ClinicRepository clinicRepository,
                           PatientClinicScheduleRepository patientClinicScheduleRepository,
                           WaitListService waitListService) {
        this.clinicRepository = clinicRepository;
        this.patientClinicScheduleRepository = patientClinicScheduleRepository;
        this.waitListService = waitListService;
    }

    public Schedule bookSchedule(Integer patientId, Schedule schedule) {
        schedule.setScheduleId(UUID.randomUUID());
        return patientClinicScheduleRepository.insert(schedule);
    }

    public void cancelSchedule(Integer patientId, Integer scheduleId) throws RuntimeException {
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

    public void addToWaitList(Integer patientId, WaitList waitList) {
        // waitList repository
        // repository.insert(schedule);
    }

    public Schedule reschduleSchdeule(Integer patientId, Integer scheduleId, Schedule schedule) {
        cancelSchedule(patientId, scheduleId);
        return bookSchedule(patientId, schedule);
    }

    public List<Schedule> getSchedules(String date) {
        return patientClinicScheduleRepository.findAllByShiftDate(date);
    }

    /***
    public List<Clinic> getSchedules(ScheduleFilter filter) {
        return clinicRepository.findClinicsByClinicIdAndShiftDate(filter.getClinicId(), filter.getDate());
    }
     ***/
}
