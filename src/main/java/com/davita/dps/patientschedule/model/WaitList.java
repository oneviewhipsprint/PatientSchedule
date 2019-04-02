package com.davita.dps.patientschedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("waitlist")
public class WaitList {
    private static final long serialVersionUID = 6510847337568566730L;

    @PrimaryKey
    private Integer id;

    private Integer patientId;
    private Date shiftDate;
    private Integer clinicId;
    private Integer chairId;
    private Integer shiftId;
    private String status; //pending, notified
    private LocalDateTime enteredDateTime = LocalDateTime.now();
}
