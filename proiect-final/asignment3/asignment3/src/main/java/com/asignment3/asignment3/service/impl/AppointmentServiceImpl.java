package com.asignment3.asignment3.service.impl;

import com.asignment3.asignment3.model.dto.AppointmentDTO;
import com.asignment3.asignment3.model.dto.map.AppointmentDTOM;
import com.asignment3.asignment3.model.entity.Appointment;
import com.asignment3.asignment3.model.mapper.AppointmentMapper;
import com.asignment3.asignment3.repository.AppointmentRepository;
import com.asignment3.asignment3.service.AppointmentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository repository;
    private final AppointmentMapper appointmentMapper;


    public AppointmentServiceImpl(AppointmentRepository repository, AppointmentMapper appointmentMapper) {
        this.appointmentMapper=appointmentMapper;
        this.repository = repository;
    }

    @Override
    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        Appointment appointment;
        if(appointmentDTO.getId() != null) {
            appointment = repository.findById(appointmentDTO.getId())
                       .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find appointment with ID: " + appointmentDTO.getId()); } );
        } else {
            appointment = new Appointment();
        }

        appointmentMapper.toEntityUpdate(appointment, appointmentDTO);
        return appointmentMapper.toDTO(repository.save(appointment));
    }

    @Override
    public void delete(Long appointment_id) {
        Appointment appointment = repository.findById(appointment_id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find appointment with ID: " + appointment_id); } );
        repository.delete(appointment);
    }


    @Override
    public List<AppointmentDTOM> findAllAppointmentsForADoctor(Long id_doctor) {
        return repository.findAll()
                .stream()
                .filter(appointment -> appointment.getDoctor().getId().equals(id_doctor))
                .map(appointmentMapper::toDTOPatient)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTOM> findAllAppointmentsForAPatient(Long id_patient) {
        return repository.findAll()
                .stream()
                .filter(appointment -> appointment.getPatient().getId().equals(id_patient))
                .map(appointmentMapper::toDTODoctor)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO findById(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find appointment with ID: " + id); } );

        return appointmentMapper.toDTO(appointment);
    }
}
