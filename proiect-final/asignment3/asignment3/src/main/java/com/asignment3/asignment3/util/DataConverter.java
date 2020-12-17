package com.asignment3.asignment3.util;

import com.asignment3.asignment3.model.dto.DrugDTO;

import java.util.List;

public interface DataConverter {
    Object[][] drugsToTable(List<DrugDTO> books);
    String[] drugsToTableColumnNames();
}
