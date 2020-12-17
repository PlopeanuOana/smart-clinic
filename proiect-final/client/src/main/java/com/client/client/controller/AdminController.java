package com.client.client.controller;

import com.client.client.DTO.*;
import com.client.client.DTO.enumeration.UserRole;
import com.client.client.DTO.map.DetailedUserDTO;
import com.client.client.DTO.map.DrugDTOT;
import com.client.client.DTO.map.UserDTOT;
import com.client.client.gateway.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    private final DrugGateway drugGateway;
    private final UserGateway userGateway;
    private final PatientGateway patientGateway;
    private final DoctorGateway doctorGateway;
    private final BankAccountGateway bankAccountGateway;

    @Autowired
    public AdminController(DrugGateway drugGateway, UserGateway userGateway, PatientGateway patientGateway, DoctorGateway doctorGateway, BankAccountGateway bankAccountGateway) {
        this.drugGateway = drugGateway;
        this.userGateway = userGateway;
        this.patientGateway = patientGateway;
        this.doctorGateway = doctorGateway;
        this.bankAccountGateway = bankAccountGateway;
    }

    @GetMapping("/admin")
    public ModelAndView test1(ModelAndView modelAndView, HttpSession httpSession){
        List<UserDTO> users = userGateway.findAll();
        List<DrugDTO> drugs = drugGateway.findAll();

        UserDTO user =(UserDTO) httpSession.getAttribute("user");
        modelAndView.addObject("user",user);
        modelAndView.addObject("users",users);
        modelAndView.addObject("drugs",drugs);

        BankAccountDTO bankAccount = bankAccountGateway.findByUserId(user.getId());
        modelAndView.addObject("bank_account", bankAccount);

        modelAndView.setViewName("admin");

        return modelAndView;
    }

    @PostMapping("/add_user_form")
    public ModelAndView addUser(ModelAndView mav, DetailedUserDTO detail){
        UserDTO user = new UserDTO();
        user.setName(detail.getName());
        user.setUsername(detail.getUsername());
        user.setPassword(detail.getPassword());
        user.setPhone(detail.getPhone());
        user.setAddress(detail.getAddress());
        if(detail.getRole().equals("PATIENT")){
            user.setRole(UserRole.PATIENT);
        }else
            if(detail.getRole().equals("DOCTOR")){
                user.setRole(UserRole.DOCTOR);
            }else   user.setRole(UserRole.ADMIN);

       UserDTO userSalvat= userGateway.save(user);

        if(detail.getRole().equals("PATIENT")){

            PatientDTO patient=new PatientDTO();
            patient.setMedical_history(detail.getDetail());
            patient.setUser(userSalvat);
            patientGateway.save(patient);
        }else
            if(detail.getRole().equals("DOCTOR")){

                DoctorDTO doctor=new DoctorDTO();
                doctor.setSpeciality(detail.getDetail());
                doctor.setConsultation_price(detail.getPrice());
                doctor.setUser(userSalvat);
                doctorGateway.save(doctor);

            }

        userGateway.save(userSalvat);

        mav.setViewName("redirect:/admin");
        return mav;
    }

    @PostMapping("/add_drug_form")
    public ModelAndView addDrug(ModelAndView mav, DrugDTO edit){
        drugGateway.save(edit);
        mav.setViewName("redirect:/admin");
        return mav;
    }

    @PostMapping("/edit_table_user")
    public ModelAndView getUserFromTable( ModelAndView mav,  SingletonDTO<String> arrayJSON) {
        Gson gson = new Gson();
        UserDTOT obj = gson.fromJson(arrayJSON.getContent(), UserDTOT.class);
        UserDTO user = userGateway.findById(Long.parseLong(obj.getUser_id()));

        user.setName(obj.getUser_name());
        user.setUsername(obj.getUser_username());
        user.setPassword(obj.getUser_password());
        user.setPhone(obj.getUser_phone());
        user.setAddress(obj.getUser_address());

        if(obj.getUser_role().equals("ADMIN")) user.setRole(UserRole.ADMIN);
        if(obj.getUser_role().equals("PATIENT")) user.setRole(UserRole.PATIENT);
        if(obj.getUser_role().equals("DOCTOR")) user.setRole(UserRole.DOCTOR);

        userGateway.save(user);

        mav.setViewName("redirect:/admin");
        return mav;
    }

    @PostMapping("/edit_table_drug")
    public ModelAndView getDrugFormTable( ModelAndView mav,  SingletonDTO<String> arrayJSON) {
        Gson gson = new Gson();
        DrugDTOT obj = gson.fromJson(arrayJSON.getContent(), DrugDTOT.class);
        DrugDTO drug = drugGateway.findById(Long.parseLong(obj.getDrug_id()));

        drug.setName(obj.getDrug_name());
        drug.setDescription(obj.getDrug_description());
        drug.setQuantity(Integer.parseInt(obj.getDrug_quantity()));
        drug.setPrice(Float.parseFloat(obj.getDrug_price()));
        drugGateway.save(drug);

        mav.setViewName("redirect:/admin");
        return mav;
    }

    @PostMapping("/delete_table_user")
    public ModelAndView deleteUser(ModelAndView mav, SingletonDTO<String> deleteJSON){
        Gson gson= new Gson();
        UserDTOT obj = gson.fromJson(deleteJSON.getContent(), UserDTOT.class);

        Long user_id  = Long.parseLong(obj.getUser_id());

        if(obj.getUser_role().equals("DOCTOR")){
            DoctorDTO doctor = doctorGateway.findByUserId(user_id);
            doctorGateway.delete(doctor.getId());
        }
        else
            if(obj.getUser_role().equals("PATIENT")){
                PatientDTO patient = patientGateway.findByUserId(user_id);
                patientGateway.delete(patient.getId());
            }
            else userGateway.delete(user_id);

        mav.setViewName("redirect:/admin");
        return mav;
    }

    @PostMapping("/delete_table_drug")
    public ModelAndView deleteDrug(ModelAndView mav, SingletonDTO<String> deleteJSON){
        Gson gson= new Gson();
        DrugDTOT obj = gson.fromJson(deleteJSON.getContent(), DrugDTOT.class);

        Long drug_id  = Long.parseLong(obj.getDrug_id());

        drugGateway.delete(drug_id);

        mav.setViewName("redirect:/admin");
        return mav;
    }

    @PostMapping("/report_csv")
    public ModelAndView report_csv_generate(ModelAndView mav){
        drugGateway.generateReport(true,false);
        mav.setViewName("redirect:/admin");
        return mav;
    }

    @PostMapping("/report_pdf")
    public ModelAndView report_pdf_generate(ModelAndView mav){
        drugGateway.generateReport(false,true);
        mav.setViewName("redirect:/admin");
        return mav;
    }

    @PostMapping("/edit_bank_account_admin")
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

        mav.setViewName("redirect:/admin");
        return mav;
    }

}
