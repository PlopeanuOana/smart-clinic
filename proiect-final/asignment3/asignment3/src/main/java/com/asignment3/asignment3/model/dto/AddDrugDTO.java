package com.asignment3.asignment3.model.dto;

public class AddDrugDTO {
    private Long id_prescription;
    private Long id_drug;
    private Integer quantity;

    public Long getId_prescription() {
        return id_prescription;
    }

    public void setId_prescription(Long id_prescription) {
        this.id_prescription = id_prescription;
    }

    public Long getId_drug() {
        return id_drug;
    }

    public void setId_drug(Long id_drug) {
        this.id_drug = id_drug;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
