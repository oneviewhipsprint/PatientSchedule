package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Integer> {}

