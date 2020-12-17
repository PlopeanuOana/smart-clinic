package com.asignment3.asignment3.service;

import com.asignment3.asignment3.model.dto.DrugDTO;
import com.asignment3.asignment3.model.factory.Report;
import com.asignment3.asignment3.model.factory.ReportFactory;

import java.util.List;

public interface DrugService {
    DrugDTO save(DrugDTO drugDTO);
    void delete(Long id);
    List<DrugDTO> findAllDrugs();
    void generateReport(boolean csv, boolean txt);
    DrugDTO findById(Long id);
}
