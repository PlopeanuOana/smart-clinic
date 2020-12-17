package com.asignment3.asignment3.model.mapper;

import com.asignment3.asignment3.model.dto.UserDTO;
import com.asignment3.asignment3.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper(uses = NameIdMapper.class)
public abstract class UserMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(target = "role", source ="role")
    public abstract UserDTO toDTO(User user);

    @Mapping(target = "name", source = "name")
    public abstract void toEntityUpdate(@MappingTarget User user, UserDTO userDTO);

}
