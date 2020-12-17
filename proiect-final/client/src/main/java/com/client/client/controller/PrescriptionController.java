package com.client.client.controller;

import com.client.client.DTO.*;
import com.client.client.DTO.map.*;
import com.client.client.gateway.AppointmentGateway;
import com.client.client.gateway.DoctorGateway;
import com.client.client.gateway.DrugGateway;
import com.client.client.gateway.PrescriptionGateway;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PrescriptionController {

    private final DrugGateway drugGateway;
    private final AppointmentGateway appointmentGateway;
    private final PrescriptionGateway prescriptionGateway;
    private final DoctorGateway doctorGateway;

    @Autowired
    public PrescriptionController(DrugGateway drugGateway, AppointmentGateway appointmentGateway, PrescriptionGateway prescriptionGateway, DoctorGateway doctor) {
        this.drugGateway = drugGateway;
        this.appointmentGateway = appointmentGateway;
        this.prescriptionGateway = prescriptionGateway;
        this.doctorGateway = doctor;
    }

    @PostMapping("/make_prescription")
    public String make_prescription(AppointmentDTO appointmentDTO,HttpSession httpSession){
        AppointmentDTO app = appointmentGateway.findById(appointmentDTO.getId());
        if(app==null) return "redirect:/doctor";
        else{
            httpSession.setAttribute("appointment",app);
            return "redirect:/prescription";
        }
    }

    @GetMapping("/prescription")
    public ModelAndView test1(ModelAndView modelAndView, HttpSession httpSession){
        List<DrugDTO> drugs = drugGateway.findAll();
        modelAndView.addObject("drugs_data",drugs);

        modelAndView.setViewName("prescription");

        return modelAndView;
    }

    @PostMapping("/add_drug_prescription_check")
    public ModelAndView addDrugPrescription(ModelAndView mav, HttpSession httpSession ,IdQuantityDTO obj){

        List<IdQuantityDTO> list = (List<IdQuantityDTO>) httpSession.getAttribute("drugsList");

        if(list==null) list=new ArrayList<>();

        list.add(obj);
        System.out.println(list);

        httpSession.setAttribute("drugsList", list);
        mav.setViewName("redirect:/prescription");
        return mav;
    }


    @PostMapping("/check_out")
    public ModelAndView check_out_prescription(ModelAndView mav, HttpSession httpSession, String name, String detail){
        List<IdQuantityDTO> list = (List<IdQuantityDTO>) httpSession.getAttribute("drugsList");

        AppointmentDTO app = (AppointmentDTO) httpSession.getAttribute("appointment");
        PrescriptionDTO prescriptionDTO= new PrescriptionDTO();
        prescriptionDTO.setAppointment(app);
        prescriptionDTO.setRecommendation(detail);

        PrescriptionDTO pres = prescriptionGateway.save(prescriptionDTO);

        for (IdQuantityDTO obj: list){
            AddDrugDTO article = new AddDrugDTO();

            article.setQuantity(Integer.parseInt(obj.getQuantity()));
            article.setId_drug(obj.getId());
            article.setId_prescription(pres.getId());
            prescriptionGateway.saveArticle(article);
        }

        CheckOutDTO check = new CheckOutDTO();
        check.setPromo(name);
        check.setAppointment_id(app.getId());
        check.setPrescription_id(pres.getId());
        check.setCard_info(true);

        prescriptionGateway.check_out(check);

        mav.setViewName("redirect:/doctor");
        return mav;
    }

}
