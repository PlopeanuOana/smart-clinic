package com.client.client.DTO.map;

import java.util.List;

public class PrescriptionDTOM {
    private Long id;
    private String doctorName;
    private String patientName;
    private String recommendation;
    private List<DrugPrescriptionDTOM> drugsPrescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DrugPrescriptionDTOM> getDrugsPrescription() {
        return drugsPrescription;
    }

    public void setDrugsPrescription(List<DrugPrescriptionDTOM> drugsPrescription) {
        this.drugsPrescription = drugsPrescription;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
