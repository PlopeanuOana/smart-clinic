package com.client.client.gateway;

import com.client.client.DTO.PatientDTO;
import com.client.client.DTO.map.AppointmentDTOM;
import com.client.client.DTO.map.PatientDTOM;

import java.util.List;

public interface PatientGateway {
    PatientDTO findById(Long id);

    PatientDTO findByUserId(Long user_id);

    PatientDTO findByUsername(String username);

    List<PatientDTOM> findAll();

    PatientDTO save(PatientDTO patientDTO);

    List<AppointmentDTOM> findAllApp(Long id);

    void delete(Long id);
}
