package com.assignment.assignment3.consultation;

import com.assignment.assignment3.consultation.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findAllByDateAndUserIdOrderByTimeAsc(LocalDate date, Long userId);
    List<Consultation> findAllByPatientIdAndUserIdAndDateNotOrderByDateDesc(Long patientId, Long userId, LocalDate date);
}