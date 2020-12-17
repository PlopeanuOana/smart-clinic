package com.asignment3.asignment3.model.dto;

public class CheckOutDTO {
    private Long prescription_id;
    private Long appointment_id;
    private String promo;
    private boolean card_info;

    public Long getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(Long prescription_id) {
        this.prescription_id = prescription_id;
    }

    public Long getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Long appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public boolean isCard_info() {
        return card_info;
    }

    public void setCard_info(boolean card_info) {
        this.card_info = card_info;
    }
}
