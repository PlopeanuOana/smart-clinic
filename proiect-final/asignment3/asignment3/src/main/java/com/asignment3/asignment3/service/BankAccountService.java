package com.asignment3.asignment3.service;

import com.asignment3.asignment3.model.dto.BankAccountDTO;

import java.util.List;

public interface BankAccountService {
    BankAccountDTO save(BankAccountDTO bankAccountDTO);
    void delete(Long id);
    List<BankAccountDTO> findAll();
    BankAccountDTO findBankAccountByUserId(Long user_id);
    BankAccountDTO findById(Long id);
}
