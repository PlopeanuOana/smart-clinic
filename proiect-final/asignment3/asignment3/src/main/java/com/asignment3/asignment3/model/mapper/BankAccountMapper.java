package com.asignment3.asignment3.model.mapper;

import com.asignment3.asignment3.model.dto.BankAccountDTO;
import com.asignment3.asignment3.model.entity.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper(uses = {NameIdMapper.class, UserMapper.class})
public abstract class BankAccountMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(source = "security_number", target="security_number")
    public abstract BankAccountDTO toDTO(BankAccount bankAccount);

    @Mapping(target = "account_number", source = "account_number")
    public abstract void toEntityUpdate(@MappingTarget BankAccount bankAccount, BankAccountDTO bankAccountDTO);

    @Mapping(source = "security_number", target="security_number")
    public abstract BankAccount toEntity(BankAccountDTO bankAccountDTO);
}
