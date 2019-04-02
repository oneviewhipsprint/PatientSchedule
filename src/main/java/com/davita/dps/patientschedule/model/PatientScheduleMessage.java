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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("patient_schedule")
public class PatientScheduleMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private UUID id;

    private String messageType;
    private Integer patientId;
    private Integer clinicId;
    private Integer chairId;
    private Integer shiftId;
    private String shiftDate;
}
