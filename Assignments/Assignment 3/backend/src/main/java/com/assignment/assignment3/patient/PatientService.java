package com.assignment.assignment3.patient;

import com.assignment.assignment3.patient.dto.PatientDTO;
import com.assignment.assignment3.patient.mapper.PatientMapper;
import com.assignment.assignment3.user.RoleRepository;
import com.assignment.assignment3.user.UserRepository;
import com.assignment.assignment3.user.dto.UserDTO;
import com.assignment.assignment3.user.mapper.UserMapper;
import com.assignment.assignment3.user.model.ERole;
import com.assignment.assignment3.user.model.Role;
import com.assignment.assignment3.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMapper ::patientToPatientDTO)
                .collect(Collectors.toList());
    }

    public void update(PatientDTO patientDTO) {
        save(patientDTO);
    }

    public void save(PatientDTO patientDTO) {
        patientRepository.save(patientMapper.patientDTOToPatient(patientDTO));
    }

    public void deleteByID(long id) {
        patientRepository.deleteById(id);
    }

    public PatientDTO findById(Long id) {
        Optional<PatientDTO> result = patientRepository.findById(id).map(patientMapper :: patientToPatientDTO);
        return result.orElse(null);
    }
}

