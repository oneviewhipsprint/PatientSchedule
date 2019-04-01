package com.davita.dps.patientschedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("clinicchair")
public class ClinicChair implements Serializable {
    private static final long serialVersionUID = -1235951356081374365L;

    private Integer clinicid;
    private Integer chairid;
}
