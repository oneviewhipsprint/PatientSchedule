package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.WaitList;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface WaitListRepository extends CassandraRepository<WaitList, UUID> {

    @AllowFiltering
    List<WaitList> getWaitListByClinicIdAndShiftDateAndShiftIdAndChairId(
            Integer clinicId, String shiftDate, Integer shiftId, Integer chairId);


}

