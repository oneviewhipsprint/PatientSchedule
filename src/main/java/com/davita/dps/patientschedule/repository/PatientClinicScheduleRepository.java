package com.davita.dps.patientschedule.repository;

import com.davita.dps.patientschedule.model.Schedule;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientClinicScheduleRepository extends CassandraRepository<Schedule, UUID> {
    @AllowFiltering
    List<Schedule> findAllByShiftDate(String date);

    @AllowFiltering
    Optional<Schedule> findByClinicIdAndShiftDateAndChairIdAndShiftId(Integer clinicId,
                                                                      String shiftDate,
                                                                      Integer chairId,
                                                                      Integer shiftId);

    @AllowFiltering
    List<Schedule>  findAllByReminderSent(boolean reminderSent);
}
