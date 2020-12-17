package com.asignment3.asignment3.validare;

import com.asignment3.asignment3.model.dto.AppointmentDTO;
import com.asignment3.asignment3.repository.AppointmentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Component
public class AppointmentValidator {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentValidator(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void validateAppointment(AppointmentDTO appointmentDTO, BindingResult result){
        LocalDateTime date = LocalDateTime.now();

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int hour = date.getHour();

        if((!StringUtils.isNotEmpty(appointmentDTO.getHour()))
                || (Integer.parseInt(appointmentDTO.getHour()) >= 20
                || Integer.parseInt(appointmentDTO.getHour()) < 8) )
            result.rejectValue("hour","Invalid hour for an appointment");

        if((!StringUtils.isNotEmpty(appointmentDTO.getHour()))
                || (year > appointmentDTO.getYear()))
            result.rejectValue("year","Invalid year for an appointment");

        if((!StringUtils.isNotEmpty(appointmentDTO.getHour()))
                || (year == appointmentDTO.getYear() && (month > appointmentDTO.getMonth())))
            result.rejectValue("month","Invalid month for an appointment");

        if((!StringUtils.isNotEmpty(appointmentDTO.getHour()))
                ||(year == appointmentDTO.getYear() && (month == appointmentDTO.getMonth()) && (day > appointmentDTO.getDay())))
            result.rejectValue("day","Invalid day for an appointment");

        if( year == appointmentDTO.getYear()
                && month == appointmentDTO.getMonth()
                && day==appointmentDTO.getDay()
                && Integer.valueOf(hour) > Integer.parseInt(appointmentDTO.getHour()))
            result.rejectValue("hour","Invalid hour for an appointment");


    }

    public boolean validDate(int day, int month, int year){
        String dateStr = String.valueOf(month)+'/'+ day +'/'+year;
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }


}
