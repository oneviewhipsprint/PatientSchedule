package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {}

