package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.Chair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairRepository extends CrudRepository<Chair, Integer> {}

