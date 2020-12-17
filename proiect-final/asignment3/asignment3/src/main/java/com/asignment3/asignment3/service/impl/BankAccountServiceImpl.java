package com.asignment3.asignment3.service.impl;

import com.asignment3.asignment3.model.dto.BankAccountDTO;
import com.asignment3.asignment3.model.entity.BankAccount;
import com.asignment3.asignment3.model.mapper.BankAccountMapper;
import com.asignment3.asignment3.repository.BankAccountRepository;
import com.asignment3.asignment3.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    public final BankAccountRepository bankAccountRepository;
    public final BankAccountMapper bankAccountMapper;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,
                                  BankAccountMapper bankAccountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
    }


    @Override
    public BankAccountDTO save(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount;
        if(bankAccountDTO.getId()!=null){
            bankAccount = bankAccountRepository.findById(bankAccountDTO.getId())
                    .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find bank account with ID: " + bankAccountDTO.getId()); } );

        }else{
            bankAccount = new BankAccount();
        }
        bankAccountMapper.toEntityUpdate(bankAccount,bankAccountDTO);
        return bankAccountMapper.toDTO(bankAccountRepository.save(bankAccount));
    }

    @Override
    public void delete(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find bank account with ID: " + id); } );
        bankAccountRepository.delete(bankAccount);
    }

    @Override
    public List<BankAccountDTO> findAll() {
        return bankAccountRepository.findAll()
                .stream().map(bankAccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountDTO findBankAccountByUserId(Long user_id) {
        return bankAccountMapper.toDTO(bankAccountRepository.findByUserId(user_id));
    }

    @Override
    public BankAccountDTO findById(Long id) {
        return bankAccountMapper.toDTO(bankAccountRepository.findById(id).orElseThrow(()
                -> { throw new EntityNotFoundException("Cannot find bank account with ID: " + id); } ));
    }
}
