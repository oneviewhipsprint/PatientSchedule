package com.davita.dps.patientschedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientScheduleMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String messageType;
    private Integer patientId;
    private Integer clinicId;
    private Integer chairId;
    private Integer shiftId;
    private String shiftDate;
    private String text;
}
