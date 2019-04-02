package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.Schedule;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientClinicScheduleRepository extends CassandraRepository<Schedule, Integer> {
    @AllowFiltering
    List<Schedule> findAllByShiftDate(String date);
}
