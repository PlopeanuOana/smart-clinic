package com.asignment3.asignment3.model.dto.map;


public class DoctorDTOM extends NameIdDTO {

    private String username;
    private String address;
    private String phone;
    private String speciality;
    private String consultation_price;

    public DoctorDTOM() {
    }

    public String getConsultation_price() {
        return consultation_price;
    }

    public void setConsultation_price(String consultation_price) {
        this.consultation_price = consultation_price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
