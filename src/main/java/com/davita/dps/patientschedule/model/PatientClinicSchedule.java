package com.davita.dps.patientschedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("schedule")
public class PatientClinicSchedule implements Serializable {

    private static final long serialVersionUID = 6510847337568566730L;

    @PrimaryKey
    private Integer scheduleId;

    private Integer patientId;
    private Integer clinicId;
    private Integer chairId;
    private Integer shiftId;
    private String shiftDate;
}
