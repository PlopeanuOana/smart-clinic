package com.client.client.gateway;

import com.client.client.DTO.DrugDTO;

import java.util.List;

public interface DrugGateway {
    List<DrugDTO> findAll();
    Long save(DrugDTO drugDTO);
    DrugDTO findById(Long drug_id);
    void delete(Long id);
    void generateReport(boolean csv, boolean pdf);
}
