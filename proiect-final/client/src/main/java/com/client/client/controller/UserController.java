package com.client.client.controller;

import com.client.client.DTO.*;
import com.client.client.DTO.enumeration.UserRole;
import com.client.client.DTO.map.*;
import com.client.client.gateway.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserGateway userGateway;
    private final PatientGateway patientGateway;
    private final DoctorGateway doctorGateway;
    private final AppointmentGateway appointmentGateway;
    private final BankAccountGateway bankAccountGateway;
    private final PrescriptionGateway prescriptionGateway;

    @Autowired
    public UserController(UserGateway userGateway,
                          PatientGateway patientGateway,
                          DoctorGateway doctorGateway,
                          AppointmentGateway appointmentGateway,
                          BankAccountGateway bankAccountGateway, PrescriptionGateway prescriptionGateway){
        this.userGateway = userGateway;
        this.patientGateway = patientGateway;
        this.doctorGateway = doctorGateway;
        this.appointmentGateway=appointmentGateway;
        this.bankAccountGateway = bankAccountGateway;
        this.prescriptionGateway = prescriptionGateway;
    }

    @PostMapping("/login")
    public String login(UserDTO userDTO, HttpSession httpSession){
        UserDTO user = userGateway.login(userDTO);

        httpSession.setAttribute("user",user);
        if(user==null){
            return "redirect:/welcome?error";
        }
        if(user.getRole().equals(UserRole.ADMIN)){
            return "redirect:/admin";
        }else
        if(user.getRole().equals(UserRole.DOCTOR))
            return "redirect:/doctor";
        else
            return "redirect:/patient";
    }
    @PostMapping("/make_appointment_form")
    public ModelAndView makeAppointment(AppointmentDataDTO appointmentDataDTO, ModelAndView modelAndView){
        DoctorDTO doctor = doctorGateway.findById(appointmentDataDTO.getDoctor_id());
        PatientDTO patient=patientGateway.findById(appointmentDataDTO.getPatient_id());
        AppointmentDTO appointmentDTO=new AppointmentDTO();
        appointmentDTO.setYear(Integer.parseInt(appointmentDataDTO.getDate().substring(0,4)));
        appointmentDTO.setMonth(Integer.parseInt(appointmentDataDTO.getDate().substring(6,7)));
        appointmentDTO.setDay(Integer.parseInt(appointmentDataDTO.getDate().substring(8,10)));
        appointmentDTO.setHour(appointmentDataDTO.getHour().substring(0,2));
        appointmentDTO.setDoctor(doctor);
        appointmentDTO.setPatient(patient);

        if(appointmentGateway.save(appointmentDTO)==null) modelAndView.setViewName("redirect:/patient?error");
        else
            modelAndView.setViewName("redirect:/patient");
        return modelAndView;
    }

    @PostMapping("/register")
    public String register(UserDTO userDTO, HttpSession httpSession){
        Long user_id = userGateway.register(userDTO);
        if(user_id == null) return "redirect:/welcome";
        else{
            UserDTO user = userGateway.findById(user_id);
            httpSession.setAttribute("user",user);
            return "redirect:/patient";
        }
    }



    @GetMapping("/patient")
    public ModelAndView patient(ModelAndView mav, HttpSession httpSession) {
        mav.setViewName("patient");

        UserDTO user =(UserDTO) httpSession.getAttribute("user");
        PatientDTO patient = patientGateway.findByUserId(user.getId());
        mav.addObject("user",user);
        mav.addObject("patient", patient);

        BankAccountDTO bankAccount = bankAccountGateway.findByUserId(user.getId());
        if(bankAccount==null){
            bankAccount=new BankAccountDTO();
        }
        mav.addObject("bank_account", bankAccount);

        List<AppointmentDTOM> app = patientGateway.findAllApp(patient.getId());
        mav.addObject("appointments",app);

        List<DoctorDTOM> doctors = doctorGateway.findAll();
        mav.addObject("doctors",doctors);

        return mav;
    }

    @GetMapping("/doctor")
    public ModelAndView doctor(ModelAndView mav,  HttpSession httpSession){
        mav.setViewName("doctor");

        UserDTO user =(UserDTO) httpSession.getAttribute("user");
        DoctorDTO doctor = doctorGateway.findByUserId(user.getId());
        mav.addObject("user",user);
        mav.addObject("doctor", doctor);

        BankAccountDTO bankAccount = bankAccountGateway.findByUserId(user.getId());
        if(bankAccount==null) bankAccount=new BankAccountDTO();
        mav.addObject("bank_account", bankAccount);

        List<AppointmentDTOM> app;
        if(doctorGateway.findAllApp(doctor.getId())==null) app=new ArrayList<AppointmentDTOM>();
        else
            app = doctorGateway.findAllApp(doctor.getId());
        mav.addObject("appointments",app);

        return mav;
    }

    @PostMapping("/show_prescription")
    public ModelAndView
    getArrayFromJavascript( ModelAndView mav, HttpSession httpSession , SingletonDTO<String> arrayJSON) {
        Gson gson = new Gson();
        AppointmentDTOC obj = gson.fromJson(arrayJSON.getContent(), AppointmentDTOC.class);
        AppointmentDTO app = appointmentGateway.findById(Long.parseLong(obj.getAp_id()));
        PrescriptionDTOM pres = prescriptionGateway.findByAppointmentId(app.getId());
        if(pres==null){
            List<IdQuantityDTO> drugsList = new ArrayList<>();
            httpSession.setAttribute("drugsList",drugsList);
            httpSession.setAttribute("appointment", app);

            mav.setViewName("redirect:/prescription");
            return mav;
        }else{
            httpSession.setAttribute("prescription", pres);
            mav.setViewName("redirect:/see_prescription");
        }
        return mav;
    }

    @PostMapping("/show_prescription_patient")
    public ModelAndView showPatientPrescription( ModelAndView mav, HttpSession httpSession , SingletonDTO<String> arrayJSON) {
        Gson gson = new Gson();
        AppointmentDTOC obj = gson.fromJson(arrayJSON.getContent(), AppointmentDTOC.class);
        AppointmentDTO app = appointmentGateway.findById(Long.parseLong(obj.getAp_id()));
        PrescriptionDTOM pres = prescriptionGateway.findByAppointmentId(app.getId());
        if(pres==null){
            mav.setViewName("redirect:/patient?error");
            return mav;
        }else{
            httpSession.setAttribute("prescription", pres);
            mav.setViewName("redirect:/see_prescription_patient");
        }
        return mav;
    }

    @PostMapping("/edit_profile_form_patient")
    public ModelAndView edit_profile_patient(ModelAndView mav, EditUserDTO edit){
        UserDTO user = userGateway.findById(edit.getId());
        PatientDTO patient = patientGateway.findByUserId(user.getId());
        user.setName(edit.getName());
        user.setAddress(edit.getAddress());
        user.setPhone(edit.getPhone_number());
        user.setUsername(edit.getUsername());
        patient.setMedical_history(edit.getSpeciality());
        patient.setAppointments(new ArrayList<>());


        patientGateway.save(patient);
        userGateway.save(user);

        mav.setViewName("redirect:/patient");
        return mav;
    }
    @PostMapping("/edit_bank_account_form_patient")
    public ModelAndView edit_bank_account_patient(ModelAndView mav, BankAccountDTO edit){
        UserDTO user = userGateway.findById(edit.getId());
        BankAccountDTO bank = bankAccountGateway.findByUserId(edit.getId());

        if(bank==null) bank=new BankAccountDTO();

        bank.setAccount_number(edit.getAccount_number());
        bank.setAmount(edit.getAmount());
        bank.setSecurity_number(edit.getSecurity_number());
        bank.setUser(user);
        bankAccountGateway.save(bank);
        userGateway.save(user);

        mav.setViewName("redirect:/patient");
        return mav;
    }

    @PostMapping("/edit_bank_account_form")
    public ModelAndView edit_bank_account(ModelAndView mav, BankAccountDTO edit){
        UserDTO user = userGateway.findById(edit.getId());
        BankAccountDTO bank = bankAccountGateway.findByUserId(edit.getId());

        if(bank==null) bank=new BankAccountDTO();

        bank.setAccount_number(edit.getAccount_number());
        bank.setAmount(edit.getAmount());
        bank.setSecurity_number(edit.getSecurity_number());
        bank.setUser(user);
        bankAccountGateway.save(bank);
        userGateway.save(user);

        mav.setViewName("redirect:/doctor");
        return mav;
    }

    @PostMapping("/edit_profile_form")
    public ModelAndView edit_profile(ModelAndView mav, EditUserDTO edit){
        UserDTO user = userGateway.findById(edit.getId());
        DoctorDTO doctor = doctorGateway.findByUserId(user.getId());
        user.setName(edit.getName());
        user.setAddress(edit.getAddress());
        user.setPhone(edit.getPhone_number());
        user.setUsername(edit.getUsername());
        doctor.setSpeciality(edit.getSpeciality());
        doctor.setAppointments(new ArrayList<>());
        userGateway.save(user);
        doctorGateway.save(doctor);


        mav.setViewName("redirect:/doctor");
        return mav;
    }

}
