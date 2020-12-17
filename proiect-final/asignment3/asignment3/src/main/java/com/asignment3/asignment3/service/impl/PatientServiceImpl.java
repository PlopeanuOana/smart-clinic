package com.asignment3.asignment3.service.impl;

import com.asignment3.asignment3.model.dto.DoctorDTO;
import com.asignment3.asignment3.model.dto.PatientDTO;
import com.asignment3.asignment3.model.dto.map.PatientDTOM;
import com.asignment3.asignment3.model.entity.Patient;
import com.asignment3.asignment3.model.mapper.PatientMapper;
import com.asignment3.asignment3.repository.PatientRepository;
import com.asignment3.asignment3.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;
    private final PatientMapper patientMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository repository,
                              PatientMapper patientMapper) {
        this.repository = repository;
        this.patientMapper = patientMapper;
    }

    @Override
    public PatientDTO save(PatientDTO patientDTO) {
        Patient patient;
        if(patientDTO.getId() != null) {
            patient = repository.findById(patientDTO.getId())
                       .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find patient with ID: " + patientDTO.getId()); } );

        } else {
            patient = new Patient();
        }

        patientMapper.toEntityUpdate(patient, patientDTO);
        return patientMapper.toDTO(repository.save(patient));
    }


    @Override
    public void delete(Long id) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find patient with ID: " + id); } );

        repository.delete(patient);
    }

    @Override
    public List<PatientDTOM> findAllPatientsDTOM() {
        return repository.findAll()
                .stream()
                .map(patientMapper::toDTOPrint)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO findByPatientId(Long id) {
        return patientMapper.toDTO(repository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find patient with ID: " + id); }) );

    }

    @Override
    public List<PatientDTO> findAll() {
            return repository.findAll()
                    .stream()
                    .map(patientMapper::toDTO)
                    .collect(Collectors.toList());

    }

    @Override
    public PatientDTO findByUserId(Long user_id) {
        return patientMapper.toDTO(repository.findByUserId(user_id));
    }

    @Override
    public PatientDTO findByUserUsername(String username) {
        return patientMapper.toDTO(repository.findByUserUsername(username));
    }
}
