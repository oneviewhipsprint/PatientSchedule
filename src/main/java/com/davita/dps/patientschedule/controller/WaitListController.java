package com.davita.dps.patientschedule.controller;

import com.davita.dps.patientschedule.model.WaitList;
import com.davita.dps.patientschedule.service.WaitListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class WaitListController {
    private WaitListService service;

    @Autowired
    public WaitListController(WaitListService service) {
        this.service = service;
    }

    @GetMapping("/patients/{patientId}/waitLists/{date}")
    public List<WaitList> getWaitLists(@PathVariable Integer patientId, @PathVariable String date) {
        return service.getWaitLists(patientId, date);
    }

    @PostMapping("/patients/{patientId}/waitLists")
    @ResponseStatus(HttpStatus.CREATED)
    public WaitList addToWaitList(@PathVariable Integer patientId, @RequestBody WaitList waitList) {
        return service.addToWaitList(patientId, waitList);
    }
}
