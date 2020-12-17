package com.asignment3.asignment3.controller;

import com.asignment3.asignment3.model.dto.BankAccountDTO;
import com.asignment3.asignment3.model.dto.PatientDTO;
import com.asignment3.asignment3.model.dto.UserDTO;
import com.asignment3.asignment3.model.dto.map.PatientDTOM;
import com.asignment3.asignment3.service.BankAccountService;
import com.asignment3.asignment3.service.PatientService;
import com.asignment3.asignment3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/patient")
public class PatientController {

    private final PatientService patientService;
    private final UserService userService;
    private final BankAccountService bankAccountService;

    @Autowired
    public PatientController(PatientService patientService, UserService userService, BankAccountService bankAccountService) {
        this.patientService = patientService;
        this.userService = userService;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/allDTOM")
    public List<PatientDTOM> printPatients()
    {
        return patientService.findAllPatientsDTOM();
    }

    @GetMapping("/patientData")
    public ResponseEntity<Object> printPatient(@RequestParam Long user_id){
        PatientDTO p = patientService.findByUserId(user_id);

        return ResponseEntity.ok(p);
    }
    @GetMapping("/find")
    public ResponseEntity<Object> findByIdDoctor(@RequestParam Long id){
        return ResponseEntity.ok(patientService.findByPatientId(id));
    }

    @GetMapping("/all")
    public List<PatientDTO> listPatients(){return patientService.findAll();}

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody PatientDTO patientDTO) {
        System.out.println(patientDTO);
        PatientDTO save = patientService.save(patientDTO);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/delete")
    public void delete( @RequestParam Long id) {
        UserDTO user = patientService.findByPatientId(id).getUser();
        BankAccountDTO bank = bankAccountService.findBankAccountByUserId(user.getId());
        if(bank==null)
            patientService.delete(id);
        else{
            bankAccountService.delete(bank.getId());
            patientService.delete(id);
        }
    }

}
