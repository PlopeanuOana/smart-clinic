package com.asignment3.asignment3.service.impl;

import com.asignment3.asignment3.model.dto.DrugDTO;
import com.asignment3.asignment3.model.entity.Drug;
import com.asignment3.asignment3.model.factory.ReportFactory;
import com.asignment3.asignment3.model.mapper.DrugMapper;
import com.asignment3.asignment3.repository.DrugRepository;
import com.asignment3.asignment3.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugServiceImpl implements DrugService {
    private final DrugRepository drugRepository;
    private final DrugMapper drugMapper;

    @Autowired
    public DrugServiceImpl(DrugRepository drugRepository, DrugMapper drugMapper) {
        this.drugRepository = drugRepository;
        this.drugMapper = drugMapper;
    }

    @Override
    public DrugDTO save(DrugDTO drugDTO) {
        Drug drug ;
        if(drugDTO.getId()!=null){
            drug = drugRepository.findById(drugDTO.getId())
                    .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find user with ID: " + drugDTO.getId()); } );

        }else{
            drug = new Drug();
        }

        drugMapper.toEntityUpdate(drug,drugDTO);
        return drugMapper.toDto(drugRepository.save(drug));
    }

    @Override
    public void delete(Long id) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find drug with ID: " + id); } );
        drugRepository.delete(drug);
    }

    @Override
    public List<DrugDTO> findAllDrugs() {
        return drugRepository.findAll()
                .stream()
                .map(drugMapper::toDto)
                .collect(Collectors.toList());
    }

    private List<DrugDTO> getAllDrugsZeroQuantity(){
        return drugRepository.findAll().stream()
                .filter(book->book.getQuantity()==0)
                .map(drugMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void generateReport(boolean csv, boolean txt) {
        ReportFactory report = new ReportFactory();
        if(csv){
            try {
                report.getReport(".csv").report(getAllDrugsZeroQuantity());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
        if(txt){
            try {
                report.getReport(".txt").report(getAllDrugsZeroQuantity());
/*                setChanged();
                notifyObservers("report");*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public DrugDTO findById(Long id) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find user with ID: " + id); } );

        return drugMapper.toDto(drug);
    }
}
