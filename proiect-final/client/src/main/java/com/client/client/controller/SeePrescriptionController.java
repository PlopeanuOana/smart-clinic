package com.client.client.controller;

import com.client.client.DTO.DrugDTO;
import com.client.client.DTO.DrugPrescriptionDTO;
import com.client.client.DTO.PrescriptionDTO;
import com.client.client.DTO.map.DrugPrescriptionDTOM;
import com.client.client.DTO.map.DrugPrescriptionDTOT;
import com.client.client.DTO.map.PrescriptionDTOM;
import com.client.client.gateway.DrugGateway;
import com.client.client.gateway.PrescriptionGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class SeePrescriptionController {
    private final PrescriptionGateway prescriptionGateway;
    private final DrugGateway drugGateway;

    @Autowired
    public SeePrescriptionController(PrescriptionGateway prescriptionGateway, DrugGateway drugGateway) {
        this.prescriptionGateway = prescriptionGateway;
        this.drugGateway = drugGateway;
    }

    @GetMapping("/see_prescription")
    public ModelAndView test1(ModelAndView modelAndView, HttpSession httpSession){
        PrescriptionDTOM pres = (PrescriptionDTOM) httpSession.getAttribute("prescription");
        modelAndView.addObject("prescription",pres);
        List<DrugPrescriptionDTOT> drugs = new ArrayList<>();
        pres.getDrugsPrescription()
                .forEach(d->{
                    DrugPrescriptionDTOT drugPrescriptionDTOT = new DrugPrescriptionDTOT();
                    drugPrescriptionDTOT.setName(d.getName());
                    drugPrescriptionDTOT.setDescription(d.getDrug().getDescription());
                    drugPrescriptionDTOT.setPrice(d.getPrice());
                    drugPrescriptionDTOT.setQuantity(d.getQuantity());
                    drugs.add(drugPrescriptionDTOT);
                });

        modelAndView.addObject("drugs_data",drugs);

        modelAndView.setViewName("see_prescription");
        return modelAndView;
    }

    @GetMapping("/see_prescription_patient")
    public ModelAndView test12(ModelAndView modelAndView, HttpSession httpSession){
        PrescriptionDTOM pres = (PrescriptionDTOM) httpSession.getAttribute("prescription");
        modelAndView.addObject("prescription",pres);
        List<DrugPrescriptionDTOT> drugs = new ArrayList<>();
        pres.getDrugsPrescription()
                .forEach(d->{
                    DrugPrescriptionDTOT drugPrescriptionDTOT = new DrugPrescriptionDTOT();
                    drugPrescriptionDTOT.setName(d.getName());
                    drugPrescriptionDTOT.setDescription(d.getDrug().getDescription());
                    drugPrescriptionDTOT.setPrice(d.getPrice());
                    drugPrescriptionDTOT.setQuantity(d.getQuantity());
                    drugs.add(drugPrescriptionDTOT);
                });

        modelAndView.addObject("drugs_data",drugs);

        modelAndView.setViewName("see_prescription_patient");
        return modelAndView;
    }
}
