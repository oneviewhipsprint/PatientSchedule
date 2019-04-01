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
public class Patient implements Serializable {
    private static final long serialVersionUID = -2936461577864383376L;

    @PrimaryKey
    private Integer patientId;

    private String firstName;
    private String lastName;
}
