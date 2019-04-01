package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientClinicScheduleRepository extends CrudRepository<Schedule, Integer> {}
