package com.asignment3.asignment3.util;

import com.asignment3.asignment3.model.dto.DrugDTO;

import java.util.List;

public class DataConverterImpl implements DataConverter {
    @Override
    public Object[][] drugsToTable(List<DrugDTO> drugs) {
        Object[][] data = new Object[drugs.size()][5];
        for (int row = 0; row < data.length; row++) {
            data[row][0] = drugs.get(row).getId();
            data[row][1] = drugs.get(row).getName();
            data[row][2] = drugs.get(row).getDescription();
            data[row][3] = drugs.get(row).getPrice();
            data[row][4] = drugs.get(row).getQuantity();
        }
        return data;
    }

    @Override
    public String[] drugsToTableColumnNames() {
        return new String[]{"Id", "Name","Description","Price","Quantity"};
    }
}
