package com.assignment.assignment3;

import com.assignment.assignment3.consultation.ConsultationRepository;
import com.assignment.assignment3.patient.PatientRepository;
import com.assignment.assignment3.user.RoleRepository;
import com.assignment.assignment3.user.UserRepository;
import com.assignment.assignment3.user.model.ERole;
import com.assignment.assignment3.user.model.Role;
import com.assignment.assignment3.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(bootstrap) {
            consultationRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            patientRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }

            User user = User.builder()
                    .username("admin")
                    .email("x@x.com")
                    .password(passwordEncoder.encode("admin"))
                    .build();

            Role role = roleRepository.findByName(ERole.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Cannot find ADMIN role"));

            user.setRole(role);
            userRepository.save(user);

            user = User.builder()
                    .username("doctor")
                    .email("x2@x2.com")
                    .password(passwordEncoder.encode("doctor"))
                    .build();

            role = roleRepository.findByName(ERole.DOCTOR)
                    .orElseThrow(() -> new RuntimeException("Cannot find DOCTOR role"));

            user.setRole(role);
            userRepository.save(user);

            user = User.builder()
                    .username("secretary")
                    .email("e@e.com")
                    .password(passwordEncoder.encode("secretary"))
                    .build();

            role = roleRepository.findByName(ERole.SECRETARY)
                    .orElseThrow(() -> new RuntimeException("Cannot find SECRETARY role"));

            user.setRole(role);
            userRepository.save(user);
        }
    }
}