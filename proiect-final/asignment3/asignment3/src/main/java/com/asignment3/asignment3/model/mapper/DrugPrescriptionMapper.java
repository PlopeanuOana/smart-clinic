package com.asignment3.asignment3.model.mapper;

import com.asignment3.asignment3.model.dto.DrugPrescriptionDTO;
import com.asignment3.asignment3.model.dto.map.DrugPrescriptionDTOM;
import com.asignment3.asignment3.model.entity.DrugPrescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper(uses = {NameIdMapper.class, PrescriptionMapper.class})
public abstract class DrugPrescriptionMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(source ="drug.name",target = "name")
    @Mapping(source="drug.price",target ="price")
    public abstract DrugPrescriptionDTOM toDTOPrint(DrugPrescription drugPrescription);

    @Mapping(source = "quantity", target="quantity")
    public abstract DrugPrescription toEntity(DrugPrescriptionDTO drugPrescriptionDTO);

    @Mapping(source = "quantity",target = "quantity")
    public abstract DrugPrescriptionDTO toDTO(DrugPrescription drugPrescription);

    @Mapping(source ="quantity",target = "quantity")
    public abstract void toEntityUpdate(@MappingTarget DrugPrescription drugPrescription, DrugPrescriptionDTO drugPrescriptionDTO);

}
