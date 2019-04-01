package com.davita.dps.patientschedule.service;

import com.davita.dps.patientschedule.model.Chair;
import com.davita.dps.patientschedule.model.ChairsAndShiftsResponse;
import com.davita.dps.patientschedule.model.ClinicChair;
import com.davita.dps.patientschedule.model.Shift;
import com.davita.dps.patientschedule.repository.ChairRepository;
import com.davita.dps.patientschedule.repository.ClinicChairRepository;
import com.davita.dps.patientschedule.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {

    private ClinicChairRepository clinicChairRepository;
    private ChairRepository chairRepository;
    private ShiftRepository shiftRepository;

    @Autowired
    public ClinicService(ClinicChairRepository clinicChairRepository, ChairRepository chairRepository, ShiftRepository shiftRepository) {
        this.clinicChairRepository = clinicChairRepository;
        this.chairRepository = chairRepository;
        this.shiftRepository = shiftRepository;
    }

    public ChairsAndShiftsResponse getChairsAndShifts(Integer clinicId) {
        ChairsAndShiftsResponse response = new ChairsAndShiftsResponse();

        List<Chair> chairs = new ArrayList<>();

        // get all chairs for the clinic
        List<ClinicChair> clinicChairs = clinicChairRepository.findByClinicid(clinicId);
        clinicChairs.forEach(clinicChair -> {
            // get the chair
            Optional<Chair> chairOpt = chairRepository.findById(clinicChair.getChairid());
            Chair chair = chairOpt.get();
            chairs.add(chair);
        });
        response.setChairs(chairs);

        // all shifts apply to all chairs
        List<Shift> shifts = new ArrayList<>();
        Iterable iterable = shiftRepository.findAll();
        iterable.forEach(shift -> {
            shifts.add((Shift) shift);
        });
        response.setShifts(shifts);

        return response;
    }
}
