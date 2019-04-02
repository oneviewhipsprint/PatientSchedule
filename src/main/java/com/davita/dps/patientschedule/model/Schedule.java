package com.davita.dps.patientschedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("schedule")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 6510847337568566730L;

    @PrimaryKey
    private UUID scheduleId;

    private Integer patientId;
    private Integer clinicId;
    private Integer chairId;
    private Integer shiftId;
    private String shiftDate;
}
