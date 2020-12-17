package com.asignment3.asignment3.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PatientDTO {
    private Long id;
    private String medical_history;
    private UserDTO user;
    private List<AppointmentDTO> appointments;

    public PatientDTO() { }

    public PatientDTO(@JsonProperty("id") Long id,
                      @JsonProperty("medical_history") String medical_history,
                      @JsonProperty("user_id") Long user_id,
                      @JsonProperty("appointments") List<AppointmentDTO> appointments) {
        this.id = id;
        this.medical_history = medical_history;
        this.user = new UserDTO();
        user.setId(user_id);
        this.appointments = appointments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedical_history() {
        return medical_history;
    }

    public void setMedical_history(String medical_history) {
        this.medical_history = medical_history;
    }

    public List<AppointmentDTO> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentDTO> appointments) {
        this.appointments = appointments;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "id=" + id +
                ", medical_history='" + medical_history + '\'' +
                ", user=" + user +
                ", appointments=" + appointments +
                '}';
    }
}
