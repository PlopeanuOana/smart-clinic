package com.asignment3.asignment3.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity(name = "patient")
public class Patient{
    @Id
    @GeneratedValue
    private Long id;
    private String medical_history;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    private List<Appointment> appointments;

    public Patient() {
    }

    public Patient(@JsonProperty("id") Long id,
                   @JsonProperty("medical_history") String medical_history,
                   @JsonProperty("user_id") Long user_id,
                   @JsonProperty("appointments") List<Appointment> appointments) {
        this.id = id;
        this.medical_history = medical_history;
        this.user = new User();
        user.setId(user_id);
        this.appointments = appointments;
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

    public void setMedical_history(String medical_history) {
        this.medical_history = medical_history;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getMedical_history() {
        return medical_history;
    }

    public User getUser() {
        return user;
    }
}
