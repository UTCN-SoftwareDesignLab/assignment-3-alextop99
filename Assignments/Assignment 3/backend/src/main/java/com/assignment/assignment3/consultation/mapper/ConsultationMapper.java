package com.assignment.assignment3.consultation.mapper;

import com.assignment.assignment3.consultation.dto.ConsultationDTO;
import com.assignment.assignment3.consultation.model.Consultation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {
    @Mapping(
            target = "user.role",
            ignore = true
    )
    Consultation consultationDTOToConsultation(ConsultationDTO consultationDTO);

    @Mapping(
            target = "user.role",
            ignore = true
    )
    ConsultationDTO consultationToConsultationDTO(Consultation consultation);
}
