package com.asignment3.asignment3.model.factory;

import com.asignment3.asignment3.model.dto.DrugDTO;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReportCSV implements Report {
    private String home = System.getProperty("user.home");

    public void report(List<DrugDTO> drugs)
    {

        File file = new File(home + "/Downloads/" + "report.csv");

        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            // create a List which contains String array
            writer.writeNext(new String[]{"Id", "Name","Description","Price","Quantity"});

            String[][] values = new String[drugs.size()][5];
            for (int row = 0; row < values.length; row++) {
                values[row][0] = drugs.get(row).getId().toString();
                values[row][1] = drugs.get(row).getName();
                values[row][2] = drugs.get(row).getDescription();
                values[row][3] = drugs.get(row).getPrice().toString();
                values[row][4] = drugs.get(row).getQuantity().toString();
                writer.writeNext(values[row]);
            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
