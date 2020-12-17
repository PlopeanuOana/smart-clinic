package com.asignment3.asignment3.repository;

import com.asignment3.asignment3.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Appointment findByDoctorId(Long doctor_id);
}
