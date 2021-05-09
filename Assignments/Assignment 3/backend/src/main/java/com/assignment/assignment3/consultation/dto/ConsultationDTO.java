package com.assignment.assignment3.consultation.dto;

import com.assignment.assignment3.patient.dto.PatientDTO;
import com.assignment.assignment3.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ConsultationDTO {
    private Long id;

    private LocalDate date;
    private Time time;

    private PatientDTO patient;
    private UserDTO user;
    private String details;
}
