package com.client.client.gateway;

import com.client.client.DTO.AppointmentDTO;
import com.client.client.DTO.BankAccountDTO;

public interface BankAccountGateway {

    BankAccountDTO findById(Long id);
    BankAccountDTO save(BankAccountDTO bankAccountDTO);
    BankAccountDTO findByUserId(Long user_id);
}
