package com.assignment.assignment3.patient;

import com.assignment.assignment3.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
