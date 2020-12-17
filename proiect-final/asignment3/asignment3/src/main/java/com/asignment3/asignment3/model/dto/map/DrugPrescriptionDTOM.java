package com.asignment3.asignment3.model.dto.map;

import com.asignment3.asignment3.model.dto.DrugDTO;

public class DrugPrescriptionDTOM extends NameIdDTO {
    private int quantity;
    private float price;
    private DrugDTO drug;

    public DrugDTO getDrug() {
        return drug;
    }

    public void setDrug(DrugDTO drug) {
        this.drug = drug;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
