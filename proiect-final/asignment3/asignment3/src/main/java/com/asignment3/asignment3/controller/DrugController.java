package com.asignment3.asignment3.controller;

import com.asignment3.asignment3.model.dto.DrugDTO;
import com.asignment3.asignment3.model.dto.UserDTO;
import com.asignment3.asignment3.model.factory.ReportFactory;
import com.asignment3.asignment3.service.DrugService;
import com.asignment3.asignment3.validare.DrugValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( value = "/drug")
public class DrugController {

    private final DrugService drugService;
    private final DrugValidator drugValidator;

    @Autowired
    public DrugController(DrugService drugService, DrugValidator drugValidator) {
        this.drugService = drugService;
        this.drugValidator = drugValidator;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody @Valid DrugDTO drugDTO, BindingResult result) {
        drugValidator.validateDrug(drugDTO,result);
        if(result.hasErrors()){
            return new ResponseEntity<Object>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        DrugDTO save = drugService.save(drugDTO);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        drugService.delete(id);
    }

    @GetMapping("/all")
    public List<DrugDTO> findAll() {
        return drugService.findAllDrugs();
    }

    @GetMapping("/report")
    public void generateReport(@RequestParam boolean csv, @RequestParam boolean txt){
         drugService.generateReport(csv,txt);
    }

    @GetMapping("/find")
    public ResponseEntity<Object> findById(@RequestParam Long id){
        DrugDTO drug = drugService.findById(id);
        if(drug!=null){
            return ResponseEntity.ok(drug);
        }
        else return null;
    }
}
