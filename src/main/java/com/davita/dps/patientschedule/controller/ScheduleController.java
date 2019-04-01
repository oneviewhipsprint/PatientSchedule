package com.davita.dps.patientschedule.controller;

import com.davita.dps.patientschedule.filter.ScheduleFilter;
import com.davita.dps.patientschedule.model.Clinic;
import com.davita.dps.patientschedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ScheduleController {
    private ScheduleService service;

    @Autowired
    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    /***
    @GetMapping("/schedules")
    public List<Clinic> getSchedules(ScheduleFilter filter) {
        return service.getSchedules(filter);
    }
    ***/

}
