package com.asignment3.asignment3.validare;

import com.asignment3.asignment3.model.dto.DrugDTO;
import com.asignment3.asignment3.repository.DrugRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class DrugValidator {

    private final DrugRepository drugRepository;

    @Autowired
    public DrugValidator(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public void validateDrug(DrugDTO drugDTO, BindingResult result){
        if(StringUtils.isEmpty(drugDTO.getName()))
            result.rejectValue("name","Invalid name of drug");
        if(StringUtils.isEmpty(drugDTO.getQuantity().toString()) || drugDTO.getQuantity() < 0)
            result.rejectValue("quantity","Invalid quantity");
        if(StringUtils.isEmpty(drugDTO.getPrice().toString()) || drugDTO.getPrice() < 0)
            result.rejectValue("price","Invalid price");
    }
}
