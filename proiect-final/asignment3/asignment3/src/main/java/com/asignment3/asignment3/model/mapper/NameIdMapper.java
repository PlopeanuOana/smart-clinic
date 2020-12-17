package com.asignment3.asignment3.model.mapper;

import com.asignment3.asignment3.model.dto.map.NameIdDTO;
import com.asignment3.asignment3.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface NameIdMapper {

    @Mapping(target = "name", source = "name")
    NameIdDTO userToNameIdRoleDTO(User user);


}
