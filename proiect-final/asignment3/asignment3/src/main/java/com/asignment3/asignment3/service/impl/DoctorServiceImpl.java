package com.asignment3.asignment3.service.impl;

import com.asignment3.asignment3.model.dto.DoctorDTO;
import com.asignment3.asignment3.model.dto.map.DoctorDTOM;
import com.asignment3.asignment3.model.entity.Doctor;
import com.asignment3.asignment3.model.mapper.DoctorMapper;
import com.asignment3.asignment3.repository.DoctorRepository;
import com.asignment3.asignment3.repository.UserRepository;
import com.asignment3.asignment3.service.DoctorService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DoctorMapper doctorMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, UserRepository userRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public DoctorDTO save(DoctorDTO doctorDTO) {

        Doctor doctor;
        if(doctorDTO.getId() != null) {
            doctor = doctorRepository.findById(doctorDTO.getId())
                    .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find doctor with ID: " + doctorDTO.getId()); } );

        } else {
            doctor = new Doctor();
        }

        doctorMapper.toEntityUpdate(doctor,doctorDTO);
        return doctorMapper.toDTO(doctorRepository.save(doctor));
    }

    @Override
    public void delete(Long id) {
        final Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find doctor with ID: " + id); } );
        doctorRepository.delete(doctor);
    }


    @Override
    public List<DoctorDTOM> findAllDoctorsDTOM() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDTOPrint)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDTO> findAllDTO() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public DoctorDTO findDoctorById(Long id) {
        return doctorMapper.toDTO(doctorRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find doctor with ID: " + id); } ));

    }

    @Override
    public DoctorDTO findByUserId(Long user_id) {
        return doctorMapper.toDTO(doctorRepository.findByUserId(user_id));
    }
}
