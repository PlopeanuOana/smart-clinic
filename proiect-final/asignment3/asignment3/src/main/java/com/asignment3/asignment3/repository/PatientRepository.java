package com.asignment3.asignment3.repository;

import com.asignment3.asignment3.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByUserId(Long user_id);
    Patient findByUserUsername(String username);
}
