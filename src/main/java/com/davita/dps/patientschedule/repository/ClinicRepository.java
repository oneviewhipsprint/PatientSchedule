package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.Clinic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends CrudRepository<Clinic, Integer> {}

