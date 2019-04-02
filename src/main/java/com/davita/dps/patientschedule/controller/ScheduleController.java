package com.davita.dps.patientschedule.controller;

import com.davita.dps.patientschedule.model.Schedule;
import com.davita.dps.patientschedule.model.WaitList;
import com.davita.dps.patientschedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class ScheduleController {
    private ScheduleService service;

    @Autowired
    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping("/schedules/{date}")
    public List<Schedule> getSchedules(@PathVariable String date) {
        return service.getSchedules(date);
    }

    @PostMapping("/patients/{patientId}/schedules")
    @ResponseStatus(HttpStatus.CREATED)
    public Schedule bookSchedule(@PathVariable Integer patientId, @RequestBody Schedule schedule) {
        return service.bookSchedule(patientId, schedule);
    }

    @PostMapping("/patients/{patientId}/schedules/{scheduleId}")
    public void cancelSchedule(@PathVariable Integer patientId, @PathVariable UUID scheduleId) {
        service.cancelSchedule(patientId, scheduleId);
    }

    @PostMapping("/patients/{patientId}/reschedules/{scheduleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Schedule rescheduleSchedule(@PathVariable Integer patientId, @PathVariable UUID scheduleId, @RequestBody Schedule schedule) {
        return service.reschduleSchdeule(patientId, scheduleId, schedule);
    }

    @PostMapping("/patients/{patientId}/waitList")
    @ResponseStatus(HttpStatus.CREATED)
    public WaitList addToWaitList(@PathVariable Integer patientId, @RequestBody WaitList waitList) {
        return service.addToWaitList(patientId, waitList);
    }

}
