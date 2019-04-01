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
@Table("shift")
public class Shift implements Serializable {
    private static final long serialVersionUID = 8192115113640484462L;

    @PrimaryKey
    private Integer shiftId;

    private String shiftName;
    private String shiftTime;

}
