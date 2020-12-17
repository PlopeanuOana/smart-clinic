package com.asignment3.asignment3.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.util.List;

@Entity(name="prescription")
public class Prescription {
    
    @Id
    @GeneratedValue
    private Long id;
    private String recommendation;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "appointment_id",referencedColumnName = "id")
    private Appointment appointment;

    @OneToMany(mappedBy = "prescription")
    List<DrugPrescription> drugsPrescription;

    public Prescription() {
    }

    public Prescription(@JsonProperty("id") Long id,
                        @JsonProperty("recommendation") String recommendation,
                        @JsonProperty("appointment_id") Long appointment_id,
                        @JsonProperty("drugPrescription")List<DrugPrescription> drugsPrescription) {
        this.id = id;
        this.recommendation = recommendation;
        this.appointment = new Appointment();
        appointment.setId(appointment_id);
        this.drugsPrescription = drugsPrescription;
    }

    public List<DrugPrescription> getDrugsPrescription() {
        return drugsPrescription;
    }

    public void setDrugsPrescription(List<DrugPrescription> drugsPrescription) {
        this.drugsPrescription = drugsPrescription;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Long getId() {
        return id;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
