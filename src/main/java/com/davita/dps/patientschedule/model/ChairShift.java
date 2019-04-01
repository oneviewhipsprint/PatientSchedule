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
@Table("chairshift")
public class ChairShift implements Serializable {
    private static final long serialVersionUID = -8585751858732310391L;

    @PrimaryKey
    private Integer chairShiftId;

    private Integer chairId;
    private Integer shiftId;
}
