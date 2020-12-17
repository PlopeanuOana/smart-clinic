package com.client.client.gateway;

import com.client.client.DTO.AppointmentDTO;


public interface AppointmentGateway {
    AppointmentDTO findById(Long appointment_id);
     AppointmentDTO save(AppointmentDTO appointmentDTO);
}
