package com.asignment3.asignment3.model.factory;

public class ReportFactory {
    public Report getReport(String fileType) {
        switch(fileType) {
            case ".txt":
                return new ReportTxt();
            case ".csv":
                return new ReportCSV();
        }
        return null;
    }
}
