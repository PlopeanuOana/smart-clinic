package com.asignment3.asignment3.controllers;

import com.asignment3.asignment3.Assignment3ApplicationTests;
import com.asignment3.asignment3.model.dto.AppointmentDTO;
import com.asignment3.asignment3.model.dto.BankAccountDTO;
import com.asignment3.asignment3.model.dto.DoctorDetailsDTO;
import com.asignment3.asignment3.validare.AppointmentValidator;
import com.asignment3.asignment3.validare.BankAccountValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerEquivalenceTest extends Assignment3ApplicationTests {


    @Autowired
    BankAccountValidator bankAccountValidator;
    @Autowired
    AppointmentValidator appointmentValidator;

    @Test
    public void AppointmentValidDate() {
        AppointmentDTO appointment1 = new AppointmentDTO(1L,2020,12,18,"15:30", 1L, 1L);
        AppointmentDTO appointment2 = new AppointmentDTO(1L,2020,500,18,"15:30", 1L, 1L);
        AppointmentDTO appointment3 = new AppointmentDTO(1L,2020,12,18,"15:30", 1L, 1L);

        // assert statements
        assertTrue(appointmentValidator.validDate(appointment1.getDay(),
                appointment1.getMonth(),
                appointment1.getYear()), "date valid");
        assertFalse(appointmentValidator.validDate(appointment2.getDay(),
                                     appointment2.getMonth(),
                                     appointment2.getYear()), "date invalid");
        assertTrue(appointmentValidator.validDate(appointment3.getDay(),
                                    appointment3.getMonth(),
                                    appointment3.getYear()), "date valid");
    }

    @Test
    public void BankAccountValidNumber(){
        BankAccountDTO bankAccount1 = new BankAccountDTO();
        BankAccountDTO bankAccount2 = new BankAccountDTO();
        BankAccountDTO bankAccount3 = new BankAccountDTO();

        bankAccount1.setAccount_number("0000000000000000");
        bankAccount2.setAccount_number("1111asd111112222");
        bankAccount3.setAccount_number("12000000000000009");

        assertEquals(16,bankAccountValidator.validAccountNumber(bankAccount1.getAccount_number()));
        assertEquals(17,bankAccountValidator.validAccountNumber(bankAccount2.getAccount_number()));
        assertNotEquals(16,bankAccountValidator.validAccountNumber(bankAccount3.getAccount_number()));
    }

    public Float consultationPrice(DoctorDetailsDTO doctor){
        if(doctor.getYears_of_experience()==0)
            return 100f;
        if(doctor.getYears_of_experience()>0 && doctor.getYears_of_experience()<=10)
            return 200f;
        if(doctor.getYears_of_experience()>10 && doctor.getYears_of_experience()<=40)
            return 300f;
        if(doctor.getYears_of_experience()>40)
            return 500f;
        return 0f;
    }

    @Test
    public void verifyDoctorPriceByYears1(){
        ControllerEquivalenceTest test = new ControllerEquivalenceTest();
        DoctorDetailsDTO doctor1 = new DoctorDetailsDTO();
        doctor1.setYears_of_experience(20);
        doctor1.setConsultation_price(300f);

        assertEquals(300f,test.consultationPrice(doctor1));
    }

    @Test
    public void verifyDoctorPriceByYears2(){
        ControllerEquivalenceTest test = new ControllerEquivalenceTest();
        DoctorDetailsDTO doctor2 = new DoctorDetailsDTO();
        doctor2.setYears_of_experience(10);
        doctor2.setConsultation_price(300f);

        assertEquals(200f,test.consultationPrice(doctor2));
    }

    @Test
    public void verifyDoctorPriceByYears3(){
        ControllerEquivalenceTest test = new ControllerEquivalenceTest();
        DoctorDetailsDTO doctor3 = new DoctorDetailsDTO();
        doctor3.setYears_of_experience(0);
        doctor3.setConsultation_price(100f);

        assertEquals(100f,test.consultationPrice(doctor3));
    }

    @Test
    public void verifyDoctorPriceByYears4(){
        ControllerEquivalenceTest test = new ControllerEquivalenceTest();
        DoctorDetailsDTO doctor4 = new DoctorDetailsDTO();
        doctor4.setYears_of_experience(50);
        doctor4.setConsultation_price(300f);

        assertEquals(500f,test.consultationPrice(doctor4));
    }

    @Test
    public void verifyDoctorPriceByYears5(){
        ControllerEquivalenceTest test = new ControllerEquivalenceTest();
        DoctorDetailsDTO doctor5 = new DoctorDetailsDTO();
        doctor5.setYears_of_experience(30);
        doctor5.setConsultation_price(300f);

        assertNotEquals(200f,test.consultationPrice(doctor5));
    }


}
