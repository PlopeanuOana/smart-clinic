package com.asignment3.asignment3.model.mapper;

import com.asignment3.asignment3.model.dto.PrescriptionDTO;
import com.asignment3.asignment3.model.dto.map.PrescriptionDTOM;
import com.asignment3.asignment3.model.entity.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper(uses = {NameIdMapper.class, AppointmentMapper.class, DrugPrescriptionMapper.class})
public abstract class PrescriptionMapper  {

    @Autowired
    EntityManager entityManager;

    @Mapping(source = "appointment.doctor.user.name", target="doctorName")
    @Mapping(source = "appointment.patient.user.name", target = "patientName")
    @Mapping(source = "drugsPrescription",target = "drugsPrescription")
    public abstract PrescriptionDTOM toDTOPrint(Prescription prescription);

    @Mapping(target = "drugsPrescription",ignore=true)
    public abstract PrescriptionDTO toDTO(Prescription prescription);

    @Mapping(target = "drugsPrescription", ignore=true)
    public abstract void toEntityUpdate(@MappingTarget Prescription prescription, PrescriptionDTO prescriptionDTO);

    @Mapping(target = "drugsPrescription",ignore = true)
    public abstract Prescription toEntity(PrescriptionDTO prescriptionDTO);
}
