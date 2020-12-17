package com.client.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class WelcomeController {

    @GetMapping("/welcome")
    public ModelAndView test1(ModelAndView modelAndView){
        modelAndView.setViewName("first");
        return modelAndView;
    }

}
