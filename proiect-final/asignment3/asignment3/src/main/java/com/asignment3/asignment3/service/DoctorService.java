package com.asignment3.asignment3.service;

import com.asignment3.asignment3.model.dto.DoctorDTO;
import com.asignment3.asignment3.model.dto.map.DoctorDTOM;

import java.util.List;

public interface DoctorService {
    DoctorDTO save(DoctorDTO doctorDTO);
    void delete(Long id);
    List<DoctorDTOM> findAllDoctorsDTOM();
    List<DoctorDTO> findAllDTO();
    DoctorDTO findDoctorById(Long id);
    DoctorDTO findByUserId(Long user_id);

}
