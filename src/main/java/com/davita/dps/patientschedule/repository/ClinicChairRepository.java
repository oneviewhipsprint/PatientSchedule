package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.ClinicChair;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicChairRepository extends CrudRepository<ClinicChair, Integer> {

    @AllowFiltering
    List<ClinicChair> findByClinicid(Integer clinicId);
}

