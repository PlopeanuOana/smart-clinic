package com.asignment3.asignment3.validare;

import com.asignment3.asignment3.model.dto.BankAccountDTO;
import com.asignment3.asignment3.repository.BankAccountRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class BankAccountValidator {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountValidator(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public void validateBankAccount(BankAccountDTO bankAccountDTO, BindingResult result){
        if(StringUtils.isNotEmpty(bankAccountDTO.getAccount_number())
                && !StringUtils.containsOnly(bankAccountDTO.getAccount_number(),"0123456789")
                && bankAccountDTO.getAccount_number().length() != 16)
            result.rejectValue("bank_account","Bank account number is not valid");

        if(!StringUtils.containsOnly(bankAccountDTO.getSecurity_number().toString(),"0123456789")
                && bankAccountDTO.getSecurity_number().toString().length() != 3)
            result.rejectValue("security_number","Security number is not valid");
    }

    public int validAccountNumber(String account_number){
        if(account_number.length() == 16){
            if (account_number.matches("^[0-9]*$")){
                return 16;
            }
            else
                return account_number.length()+1;
        }else
            return account_number.length();
    }
}
