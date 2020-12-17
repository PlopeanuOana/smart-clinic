package com.asignment3.asignment3.service;


import com.asignment3.asignment3.model.dto.AppointmentDTO;
import com.asignment3.asignment3.model.dto.map.AppointmentDTOM;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO save(AppointmentDTO appointmentDTO);
    void delete(Long appointment_id);
    List<AppointmentDTOM> findAllAppointmentsForADoctor(Long id_doctor);
    List<AppointmentDTOM> findAllAppointmentsForAPatient(Long id_patient);
    List<AppointmentDTO> findAll();
    AppointmentDTO findById(Long id);
}
