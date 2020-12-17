package com.asignment3.asignment3.model.mapper;

import com.asignment3.asignment3.model.dto.AppointmentDTO;
import com.asignment3.asignment3.model.dto.PatientDTO;
import com.asignment3.asignment3.model.dto.PrescriptionDTO;
import com.asignment3.asignment3.model.dto.map.PatientDTOM;
import com.asignment3.asignment3.model.entity.Appointment;
import com.asignment3.asignment3.model.entity.Patient;
import com.asignment3.asignment3.model.entity.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper(uses ={ NameIdMapper.class, UserMapper.class, AppointmentMapper.class})
public abstract class PatientMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(target = "name", source ="user.name")
    @Mapping(target = "username", source="user.username")
    @Mapping(target = "phone", source="user.phone")
    @Mapping(target = "address", source="user.address")
    public abstract PatientDTOM toDTOPrint(Patient patient);

    @Mapping(target = "appointments", ignore = true)
    public abstract PatientDTO toDTO(Patient patient);

    @Mapping(target = "appointments", ignore = true)
    public abstract void toEntityUpdate(@MappingTarget Patient patient, PatientDTO patientDTO);

    @Mapping(target = "appointments", ignore = true)
    public abstract Patient toEntity(PatientDTO patientDTO);
}
