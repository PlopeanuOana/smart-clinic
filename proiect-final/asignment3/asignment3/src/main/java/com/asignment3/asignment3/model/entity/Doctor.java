package com.asignment3.asignment3.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity(name ="doctor")
public class Doctor {
    @Id
    @GeneratedValue
    private Long id;
    private String speciality;
    private Float consultation_price;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "doctor")
    private List<Appointment> appointments;

    public Doctor() {
    }

    public Float getConsultation_price() {
        return consultation_price;
    }

    public void setConsultation_price(Float consultation_price) {
        this.consultation_price = consultation_price;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", speciality='" + speciality + '\'' +
                ", user=" + user +
                ", appointments=" + appointments +
                '}';
    }
}
