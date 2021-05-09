package com.assignment.assignment3.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String idcardnumber;
    private String cnp;
    private String birthdate;
    private String address;
}
