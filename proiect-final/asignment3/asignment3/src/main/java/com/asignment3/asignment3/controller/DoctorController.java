package com.asignment3.asignment3.controller;

import com.asignment3.asignment3.model.dto.BankAccountDTO;
import com.asignment3.asignment3.model.dto.DoctorDTO;
import com.asignment3.asignment3.model.dto.UserDTO;
import com.asignment3.asignment3.model.dto.map.DoctorDTOM;
import com.asignment3.asignment3.model.entity.Doctor;
import com.asignment3.asignment3.service.BankAccountService;
import com.asignment3.asignment3.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final BankAccountService bankAccountService;

    @Autowired
    public DoctorController(DoctorService doctorService, BankAccountService bankAccountService) {
        this.doctorService = doctorService;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/allDTOM")
    public List<DoctorDTOM> printDoctors()
    {
        return doctorService.findAllDoctorsDTOM();
    }

    @GetMapping("/all")
    public List<DoctorDTO> printDoctorsDTO()
    {
        return doctorService.findAllDTO();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody DoctorDTO doctorDTO) {
        System.out.println(doctorDTO);
        DoctorDTO save = doctorService.save(doctorDTO);

        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/delete")
    public void delete( @RequestParam Long id) {
        UserDTO user = doctorService.findDoctorById(id).getUser();
        BankAccountDTO bank = bankAccountService.findBankAccountByUserId(user.getId());
        if(bank==null)
            doctorService.delete(id);
        else{
            bankAccountService.delete(bank.getId());
            doctorService.delete(id);
        }
    }

    @GetMapping("/doctorData")
    public ResponseEntity<Object> print_data(@RequestParam Long user_id){
        return ResponseEntity.ok(doctorService.findByUserId(user_id));
    }

    @GetMapping("/find")
    public ResponseEntity<Object> findByIdDoctor(@RequestParam Long id){
        return ResponseEntity.ok(doctorService.findDoctorById(id));
    }

}
