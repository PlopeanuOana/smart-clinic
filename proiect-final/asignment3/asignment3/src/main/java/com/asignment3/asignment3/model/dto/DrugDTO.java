package com.asignment3.asignment3.model.dto;

import com.asignment3.asignment3.model.dto.map.NameIdDTO;

public class DrugDTO extends NameIdDTO {

    private String description;
    private Float price;
    private Integer quantity;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }


}
