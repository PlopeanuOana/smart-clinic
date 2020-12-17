package com.client.client.DTO.map;

public class IdQuantityDTO {
    private Long id;
    private String quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "IdQuantityDTO{" +
                "id=" + id +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
