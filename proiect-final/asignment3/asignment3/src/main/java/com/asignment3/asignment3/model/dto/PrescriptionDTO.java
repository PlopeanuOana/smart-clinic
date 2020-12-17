package com.asignment3.asignment3.model.dto;

import com.asignment3.asignment3.model.dto.map.DrugPrescriptionDTOM;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PrescriptionDTO {

    private Long id;
    private String recommendation;
    private AppointmentDTO appointment;
    List<DrugPrescriptionDTOM> drugsPrescription;

    public PrescriptionDTO() {
    }

    public PrescriptionDTO(@JsonProperty("id") Long id,
                        @JsonProperty("recommendation") String recommendation,
                        @JsonProperty("appointment_id") Long appointment_id,
                        @JsonProperty("drugPrescription")List<DrugPrescriptionDTOM> drugsPrescription) {
        this.id = id;
        this.recommendation = recommendation;
        this.appointment = new AppointmentDTO();
        appointment.setId(appointment_id);
        this.drugsPrescription = drugsPrescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public AppointmentDTO getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentDTO appointment) {
        this.appointment = appointment;
    }

    public List<DrugPrescriptionDTOM> getDrugsPrescription() {
        return drugsPrescription;
    }

    public void setDrugsPrescription(List<DrugPrescriptionDTOM> drugsPrescription) {
        this.drugsPrescription = drugsPrescription;
    }
}
