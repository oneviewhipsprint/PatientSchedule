package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.PatientScheduleMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientScheduleMessageRepository extends CrudRepository<PatientScheduleMessage, UUID> {}
