package com.asignment3.asignment3.service;

import com.asignment3.asignment3.model.dto.PatientDTO;
import com.asignment3.asignment3.model.dto.map.PatientDTOM;
import com.asignment3.asignment3.model.entity.Patient;

import java.util.List;

public interface PatientService {

    PatientDTO save(PatientDTO patientDTO);

    void delete(Long id);

    List<PatientDTOM> findAllPatientsDTOM();

    PatientDTO findByPatientId(Long id);

    List<PatientDTO> findAll();

    PatientDTO findByUserId(Long user_id);

    PatientDTO findByUserUsername(String username);

}
