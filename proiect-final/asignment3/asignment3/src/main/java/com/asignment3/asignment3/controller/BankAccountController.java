package com.asignment3.asignment3.controller;

import com.asignment3.asignment3.model.dto.BankAccountDTO;
import com.asignment3.asignment3.service.BankAccountService;
import com.asignment3.asignment3.validare.BankAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( value = "/bank_account")
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final BankAccountValidator bankAccountValidator;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService,
                                 BankAccountValidator bankAccountValidator) {
        this.bankAccountService = bankAccountService;
        this.bankAccountValidator = bankAccountValidator;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody @Valid BankAccountDTO bankAccountDTO, BindingResult result) {
        bankAccountValidator.validateBankAccount(bankAccountDTO,result);

        if(result.hasErrors()) {
            return new ResponseEntity<Object>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BankAccountDTO save = bankAccountService.save(bankAccountDTO);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        bankAccountService.delete(id);
    }

    @GetMapping("/all")
    public List<BankAccountDTO> findAll(){
        return bankAccountService.findAll();
    }

    @GetMapping("/byUser")
    public BankAccountDTO findByUser(@RequestParam Long id){return bankAccountService.findBankAccountByUserId(id);}

    @GetMapping("/find")
    public BankAccountDTO findById(@RequestParam Long id){return bankAccountService.findById(id);}

}
