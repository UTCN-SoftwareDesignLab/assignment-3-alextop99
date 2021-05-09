package com.assignment.assignment3.controllers;

import com.assignment.assignment3.consultation.ConsultationService;
import com.assignment.assignment3.consultation.dto.ConsultationDTO;
import com.assignment.assignment3.patient.PatientService;
import com.assignment.assignment3.patient.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static com.assignment.assignment3.UrlMapping.*;
import static com.assignment.assignment3.UrlMapping.FIND_BY_ID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(CONSULTATIONS)
@RequiredArgsConstructor
public class ConsultationController {
    private final ConsultationService consultationService;

    @GetMapping(FIND_ALL)
    @Secured({"SECRETARY", "DOCTOR"})
    public List<ConsultationDTO> allConsultations() {
        return consultationService.findAll();
    }

    @GetMapping(FIND_ALL_BY_DATE_AND_DOCTOR)
    @Secured({"SECRETARY", "DOCTOR"})
    public List<ConsultationDTO> allConsultationsByDateAndDoctor(@RequestParam String date, @RequestParam Long id) {
        return consultationService.findAllByDateAndDoctorId(LocalDate.parse(date), id);
    }

    @GetMapping(FIND_ALL_BY_PATIENT_AND_DOCTOR)
    @Secured({"SECRETARY", "DOCTOR"})
    public List<ConsultationDTO> allConsultationsByPatientAndDoctor(@RequestParam Long patient, @RequestParam Long doctor, @RequestParam String date) {
        return consultationService.findAllByPatientIdAndUserId(patient, doctor, LocalDate.parse(date));
    }

    @DeleteMapping(DELETE)
    @Secured({"SECRETARY", "DOCTOR"})
    public void delete(@PathVariable Long id) {
        consultationService.deleteByID(id);
    }

    @PostMapping(SAVE)
    @Secured({"SECRETARY", "DOCTOR"})
    public void save(@Valid @RequestBody ConsultationDTO consultationDTO) {
        consultationService.save(consultationDTO);
    }

    @PatchMapping(UPDATE)
    @Secured({"SECRETARY", "DOCTOR"})
    public void update(@Valid @RequestBody ConsultationDTO consultationDTO) {
        consultationService.update(consultationDTO);
    }

    @GetMapping(FIND_BY_ID)
    @Secured({"SECRETARY", "DOCTOR"})
    public ConsultationDTO findById(@PathVariable Long id) {
        return consultationService.findById(id);
    }
}