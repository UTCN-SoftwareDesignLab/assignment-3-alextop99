package com.assignment.assignment3.patient.mapper;

import com.assignment.assignment3.patient.dto.PatientDTO;
import com.assignment.assignment3.patient.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient patientDTOToPatient(PatientDTO patientDTO);
    PatientDTO patientToPatientDTO(Patient patient);
}
