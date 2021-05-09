package com.assignment.assignment3.consultation;

import com.assignment.assignment3.consultation.dto.ConsultationDTO;
import com.assignment.assignment3.consultation.mapper.ConsultationMapper;
import com.assignment.assignment3.consultation.model.Consultation;
import com.assignment.assignment3.user.UserRepository;
import com.assignment.assignment3.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final UserRepository userRepository;
    private final ConsultationMapper consultationMapper;

    public List<ConsultationDTO> findAll() {
        return consultationRepository.findAll().stream()
                .map(consultationMapper ::consultationToConsultationDTO)
                .collect(Collectors.toList());
    }

    public List<ConsultationDTO> findAllByDateAndDoctorId(LocalDate date, Long doctorId) {
        return consultationRepository.findAllByDateAndUserIdOrderByTimeAsc(date, doctorId).stream()
                .map(consultationMapper ::consultationToConsultationDTO)
                .collect(Collectors.toList());
    }

    public List<ConsultationDTO> findAllByPatientIdAndUserId(Long patientId, Long doctorId, LocalDate date) {
        return consultationRepository.findAllByPatientIdAndUserIdAndDateNotOrderByDateDesc(patientId, doctorId, date).stream()
                .map(consultationMapper ::consultationToConsultationDTO)
                .collect(Collectors.toList());
    }

    public void update(ConsultationDTO consultationDTO) {
        save(consultationDTO);
    }

    public void save(ConsultationDTO consultationDTO) {
        Consultation consultation = consultationMapper.consultationDTOToConsultation(consultationDTO);
        User user = userRepository.findById(consultation.getUser().getId()).get();

        consultation.setUser(user);

        consultationRepository.save(consultation);
    }

    public void deleteByID(long id) {
        consultationRepository.deleteById(id);
    }

    public ConsultationDTO findById(Long id) {
        Optional<ConsultationDTO> result = consultationRepository.findById(id).map(consultationMapper :: consultationToConsultationDTO);
        return result.orElse(null);
    }
}
