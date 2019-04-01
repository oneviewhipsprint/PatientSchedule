package com.davita.dps.patientschedule.controller;

import com.davita.dps.patientschedule.model.ChairsAndShiftsResponse;
import com.davita.dps.patientschedule.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ClinicController {

    private ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/clinic/{clinicId}")
    public ChairsAndShiftsResponse getClinicChairsAndShifts(@PathVariable Integer clinicId) {
        return clinicService.getChairsAndShifts(clinicId);
    }

}
