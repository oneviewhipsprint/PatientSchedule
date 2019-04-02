package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.WaitList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WaitListRepository extends CrudRepository<WaitList, Integer> {

    List<WaitList> getWaitListByClinicIdAndShiftDateAndShiftIdAndChairIdOrderByEnteredDateTime(
            Integer clinicId, Date shiftDate, Integer shiftId, Integer chairId);
}
