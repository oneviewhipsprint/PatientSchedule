package com.davita.dps.patientschedule.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ChairsAndShiftsResponse {
    List<Chair> chairs;
    List<Shift> shifts;
}
