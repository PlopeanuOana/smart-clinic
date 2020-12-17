package com.client.client.controller;

import com.client.client.DTO.DrugDTO;
import com.client.client.gateway.DrugGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class DrugsController {

    private DrugGateway drugGateway;
    @Autowired
    public DrugsController(DrugGateway drugGateway) {
        this.drugGateway = drugGateway;
    }

    @GetMapping("/drugs")
    public ModelAndView test1(ModelAndView modelAndView){
        List<DrugDTO> drugs = drugGateway.findAll();
        modelAndView.addObject("drugs_data",drugs);

        modelAndView.setViewName("drugs");
        return modelAndView;
    }


}
