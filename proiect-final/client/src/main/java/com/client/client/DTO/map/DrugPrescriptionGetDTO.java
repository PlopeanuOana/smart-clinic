package com.client.client.DTO.map;

public class DrugPrescriptionGetDTO {
    private String d_id;
    private String d_name;
    private String d_description;
    private String d_price;

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_description() {
        return d_description;
    }

    public void setD_description(String d_description) {
        this.d_description = d_description;
    }

    public String getD_price() {
        return d_price;
    }

    public void setD_price(String d_price) {
        this.d_price = d_price;
    }
}
