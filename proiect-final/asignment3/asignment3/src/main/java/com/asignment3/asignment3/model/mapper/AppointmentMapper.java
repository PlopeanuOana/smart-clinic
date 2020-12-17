package com.asignment3.asignment3.model.mapper;

import com.asignment3.asignment3.model.dto.AppointmentDTO;
import com.asignment3.asignment3.model.dto.AppointmentDTOCheck;
import com.asignment3.asignment3.model.dto.PatientDTO;
import com.asignment3.asignment3.model.dto.map.AppointmentDTOM;
import com.asignment3.asignment3.model.entity.Appointment;
import com.asignment3.asignment3.model.entity.Doctor;
import com.asignment3.asignment3.model.entity.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;


@Mapper(uses = {NameIdMapper.class, PatientMapper.class, DoctorMapper.class, PrescriptionMapper.class})
public abstract class AppointmentMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(source = "doctor.user.name", target="name")
    @Mapping(source = "doctor.speciality", target="speciality")
    @Mapping(target="medical_history", ignore=true)
    public abstract AppointmentDTOM toDTODoctor(Appointment appointment);

    @Mapping(source = "patient.user.name", target="name")
    @Mapping(source = "patient.medical_history", target="medical_history")
    @Mapping(target="speciality", ignore=true)
    public abstract AppointmentDTOM toDTOPatient(Appointment appointment);

    @Mapping(source = "doctor",target = "doctor")
    public abstract AppointmentDTO toDTO(Appointment appointment);

    @Mapping(source = "doctor",target = "doctor")
    public abstract void toEntityUpdate(@MappingTarget Appointment appointment, AppointmentDTO appointmentDTO);

    @Mapping(source = "doctor",target = "doctor")
    public abstract Appointment toEntity(AppointmentDTO appointmentDTO);

    @Mapping(source ="hour", target = "hour")
    public abstract AppointmentDTOCheck toDTOCheck(Appointment appointment);
}
