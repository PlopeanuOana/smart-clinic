package com.asignment3.asignment3.controller;

import com.asignment3.asignment3.model.dto.AddDrugDTO;
import com.asignment3.asignment3.model.dto.AppointmentDTO;
import com.asignment3.asignment3.model.dto.CheckOutDTO;
import com.asignment3.asignment3.model.dto.PrescriptionDTO;
import com.asignment3.asignment3.model.dto.map.PrescriptionDTOM;
import com.asignment3.asignment3.service.AppointmentService;
import com.asignment3.asignment3.service.DoctorService;
import com.asignment3.asignment3.service.PatientService;
import com.asignment3.asignment3.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final AppointmentService appointmentService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService, AppointmentService appointmentService) {
        this.prescriptionService = prescriptionService;
        this.appointmentService = appointmentService;
    }
    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody PrescriptionDTO prescriptionDTO){
        PrescriptionDTO save = prescriptionService.save(prescriptionDTO);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/all")
    public List<PrescriptionDTOM> printPatients()
    {
        return prescriptionService.findAllPrescriptions();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        prescriptionService.delete(id);
    }

    @PostMapping(value = "/addDrug")
    public boolean saveArticle(@RequestBody AddDrugDTO addDrug){
        if(prescriptionService.addDrug(addDrug.getId_prescription(),
                addDrug.getId_drug(),addDrug.getQuantity())) return true;
        else return false;
    }

    @GetMapping(value="find")
    public PrescriptionDTOM findById(@RequestParam Long id){
        return prescriptionService.findByPrescriptionId(id);
    }

    @PostMapping(value="/check_out")
    public boolean check_out(@RequestBody CheckOutDTO check){

        PrescriptionDTOM prescriptionDTO = prescriptionService.findByPrescriptionId(check.getPrescription_id());
        AppointmentDTO appointmentDTO = appointmentService.findById(check.getAppointment_id());


        return prescriptionService.transferDrugsToPatient(prescriptionDTO,appointmentDTO,check.isCard_info(),check.getPromo());
    }

    @GetMapping(value="findByApp")
    public PrescriptionDTOM findByIdApp(@RequestParam Long id){
        return prescriptionService.findByAppointmentId(id);
    }


}
