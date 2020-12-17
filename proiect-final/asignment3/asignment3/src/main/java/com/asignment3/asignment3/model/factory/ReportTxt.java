package com.asignment3.asignment3.model.factory;

import com.asignment3.asignment3.model.dto.DrugDTO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportTxt implements Report {
    private String home = System.getProperty("user.home");

    @Override
    public void report(List<DrugDTO> drugs) throws IOException {
        File output = new File(home + "/Downloads/" + "report.txt");
        PrintWriter writer = new PrintWriter(output);
        drugs.stream()
                .forEach(drug->writer.println(drug.toString()));

        writer.close();
    }
}
