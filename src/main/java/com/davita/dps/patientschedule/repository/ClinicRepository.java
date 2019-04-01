package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.Clinic;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends CassandraRepository<Clinic, Integer> {

    /***
    @AllowFiltering
    List<Clinic> findClinicsByClinicIdAndShiftDate(Integer id, String date);
    ***/
}