package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.ClinicChair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicChairRepository extends CrudRepository<ClinicChair, Integer> {}

