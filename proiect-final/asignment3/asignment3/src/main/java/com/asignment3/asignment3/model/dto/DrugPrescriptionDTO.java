package com.asignment3.asignment3.model.dto;

import com.asignment3.asignment3.model.entity.Drug;
import com.asignment3.asignment3.model.entity.Prescription;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class DrugPrescriptionDTO {
    private Long id;
    private Integer quantity;
    private PrescriptionDTO prescription;
    private DrugDTO drug;

    public DrugPrescriptionDTO() {
    }

    public DrugPrescriptionDTO(@JsonProperty("id") Long id,
                            @JsonProperty("quantity") Integer quantity,
                            @JsonProperty("prescription_id") Long prescription_id,
                            @JsonProperty("drug_id") Long drug_id) {
        this.id = id;
        this.quantity = quantity;
        this.prescription = new PrescriptionDTO();
        prescription.setId(prescription_id);
        this.drug = new DrugDTO();
        drug.setId(drug_id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public PrescriptionDTO getPrescription() {
        return prescription;
    }

    public void setPrescription(PrescriptionDTO prescription) {
        this.prescription = prescription;
    }

    public DrugDTO getDrug() {
        return drug;
    }

    public void setDrug(DrugDTO drug) {
        this.drug = drug;
    }
}
