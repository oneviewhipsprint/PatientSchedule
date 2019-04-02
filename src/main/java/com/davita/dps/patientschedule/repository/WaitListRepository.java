package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.WaitList;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitListRepository extends CassandraRepository<WaitList, Integer> {
}

