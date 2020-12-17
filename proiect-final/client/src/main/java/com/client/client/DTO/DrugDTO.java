package com.client.client.DTO;


import com.client.client.DTO.map.NameIdDTO;

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

    @Override
    public String toString() {
        return "DrugDTO{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
