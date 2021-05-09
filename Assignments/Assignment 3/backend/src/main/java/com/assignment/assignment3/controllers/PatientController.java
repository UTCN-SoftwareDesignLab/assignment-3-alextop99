package com.assignment.assignment3.controllers;

import com.assignment.assignment3.patient.PatientService;
import com.assignment.assignment3.patient.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.assignment.assignment3.UrlMapping.*;
import static com.assignment.assignment3.UrlMapping.FIND_BY_ID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(PATIENTS)
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping(FIND_ALL)
    @Secured({"ADMIN", "SECRETARY"})
    public List<PatientDTO> allItems() {
        return patientService.findAll();
    }

    @DeleteMapping(DELETE)
    @Secured({"ADMIN", "SECRETARY"})
    public void delete(@PathVariable Long id) {
        patientService.deleteByID(id);
    }

    @PostMapping(SAVE)
    @Secured({"ADMIN", "SECRETARY"})
    public void save(@Valid @RequestBody PatientDTO patientDTO) {
        patientService.save(patientDTO);
    }

    @PatchMapping(UPDATE)
    @Secured({"ADMIN", "SECRETARY"})
    public void update(@Valid @RequestBody PatientDTO patientDTO) {
        patientService.update(patientDTO);
    }

    @GetMapping(FIND_BY_ID)
    @Secured({"ADMIN", "SECRETARY"})
    public PatientDTO findById(@PathVariable Long id) {
        return patientService.findById(id);
    }

}
