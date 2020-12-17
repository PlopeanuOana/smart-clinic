package com.client.client.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DoctorDTO {

    private Long id;
    private String speciality;
    private Float consultation_price;
    private UserDTO user;
    private List<AppointmentDTO> appointments;

    public DoctorDTO() {
    }

    public DoctorDTO(@JsonProperty("id") Long id,
                     @JsonProperty("speciality") String speciality,
                     @JsonProperty("consultation_price") Float consultation_price,
                     @JsonProperty("user_id") Long user_id,
                     @JsonProperty("appointments") List<AppointmentDTO> appointments) {
        this.id = id;
        this.speciality = speciality;
        this.consultation_price=consultation_price;
        this.user = new UserDTO();
        user.setId(user_id);
        this.appointments = appointments;
    }

    public Float getConsultation_price() {
        return consultation_price;
    }

    public void setConsultation_price(Float consultation_price) {
        this.consultation_price = consultation_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<AppointmentDTO> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentDTO> appointments) {
        this.appointments = appointments;
    }
}
