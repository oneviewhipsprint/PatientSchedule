package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.ChairShift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairShiftRepository extends CrudRepository<ChairShift, Integer> {}

