package com.asignment3.asignment3.repository;

import com.asignment3.asignment3.model.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends JpaRepository<Drug,Long> { }
