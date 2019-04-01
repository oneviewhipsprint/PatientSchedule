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
@Table("clinic")
public class Clinic implements Serializable  {
    private static final long serialVersionUID = -6892927105708095940L;

    @PrimaryKey
    private Integer clinicId;

    private String clinicName;
    private Integer chairId;
    private Integer shiftId;
    private String shiftName;
    private String shiftDate;
    private String shiftTime;

}
