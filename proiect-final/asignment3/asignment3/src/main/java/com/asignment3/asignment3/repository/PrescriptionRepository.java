package com.asignment3.asignment3.repository;

import com.asignment3.asignment3.model.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
    Prescription findByAppointmentId(Long appointment_id);
}
