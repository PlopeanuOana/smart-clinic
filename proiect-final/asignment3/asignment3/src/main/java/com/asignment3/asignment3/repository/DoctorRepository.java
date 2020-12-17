package com.asignment3.asignment3.repository;

import com.asignment3.asignment3.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Doctor findByUserId(Long user_id);

}
