package com.asignment3.asignment3.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name="drug_prescription")
public class DrugPrescription {

    @Id
    @GeneratedValue
    private Long id;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="prescription_id",referencedColumnName = "id")
    private Prescription prescription;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="drug_id",referencedColumnName = "id")
    private Drug drug;

    public DrugPrescription() {
    }

    public DrugPrescription(@JsonProperty("id") Long id,
                            @JsonProperty("quantity") Integer quantity,
                            @JsonProperty("prescription_id") Long prescription_id,
                            @JsonProperty("drug_id") Long drug_id) {
        this.id = id;
        this.quantity = quantity;
        this.prescription = new Prescription();
        prescription.setId(prescription_id);
        this.drug = new Drug();
        drug.setId(drug_id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public Drug getDrug() {
        return drug;
    }
}
