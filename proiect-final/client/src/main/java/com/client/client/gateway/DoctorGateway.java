package com.client.client.gateway;

import com.client.client.DTO.DoctorDTO;
import com.client.client.DTO.PatientDTO;
import com.client.client.DTO.map.AppointmentDTOM;
import com.client.client.DTO.map.DoctorDTOM;


import java.util.List;

public interface DoctorGateway {
    DoctorDTO findById(Long id);

    DoctorDTO findByUserId(Long user_id);

    List<DoctorDTOM> findAll();

    DoctorDTO save(DoctorDTO doctorDTO);

    List<AppointmentDTOM> findAllApp(Long id);

    void delete(Long id);
}
