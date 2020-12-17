package com.asignment3.asignment3.model.mapper;

import com.asignment3.asignment3.model.dto.DoctorDTO;
import com.asignment3.asignment3.model.dto.map.DoctorDTOM;
import com.asignment3.asignment3.model.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper(uses ={ NameIdMapper.class, UserMapper.class, AppointmentMapper.class})
public abstract class DoctorMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(target = "name", source ="user.name")
    @Mapping(target = "username", source="user.username")
    @Mapping(target = "phone", source="user.phone")
    @Mapping(target = "address", source="user.address")
    public abstract DoctorDTOM toDTOPrint(Doctor doctor);

    @Mapping(target = "appointments", ignore = true)
    public abstract DoctorDTO toDTO(Doctor doctor);

    @Mapping(target = "appointments", ignore = true)
    public abstract void toEntityUpdate(@MappingTarget Doctor doctor, DoctorDTO doctorDTO);

    @Mapping(target = "appointments", ignore = true)
    public abstract Doctor toEntity(DoctorDTO doctorDTO);
}
