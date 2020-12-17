package com.asignment3.asignment3.model.mapper;

import com.asignment3.asignment3.model.dto.DrugDTO;
import com.asignment3.asignment3.model.entity.Drug;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper(uses = NameIdMapper.class)
public abstract class DrugMapper{

    @Autowired
    EntityManager entityManager;

    @Mapping(source = "price", target="price")
    public abstract DrugDTO toDto(Drug drug);

    @Mapping(target = "name", source = "name")
    public abstract void toEntityUpdate(@MappingTarget Drug drug, DrugDTO drugDTO);

    @Mapping(source = "price", target="price")
    public abstract Drug toEntity(DrugDTO drugDTO);
}
