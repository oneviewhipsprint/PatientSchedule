package com.davita.dps.patientschedule.service;

import com.davita.dps.patientschedule.filter.ScheduleFilter;
import com.davita.dps.patientschedule.model.Clinic;
import com.davita.dps.patientschedule.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private ClinicRepository clinicRepository;

    @Autowired
    public ScheduleService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public List<Clinic> getSchedules(ScheduleFilter filter) {
        return clinicRepository.findClinicsByClinicIdAndShiftDate(filter.getClinicId(), filter.getDate());
    }
}
