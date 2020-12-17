package com.asignment3.asignment3.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AppointmentDTO {

    private Long id;
    private int year;
    private int month;
    private int day;
    private String hour;
    private DoctorDTO doctor;
    private PatientDTO patient;

    public AppointmentDTO() {
    }

    public AppointmentDTO(@JsonProperty("id") Long id,
                       @JsonProperty("year") int year,
                       @JsonProperty("month") int month,
                       @JsonProperty("day") int day,
                       @JsonProperty("hour") String hour,
                       @JsonProperty("doctor_id") Long doctor_id,
                       @JsonProperty("patient_id") Long patient_id) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.doctor = new DoctorDTO();
        doctor.setId(doctor_id);
        this.patient = new PatientDTO();
        patient.setId(patient_id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
        this.doctor = doctor;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }
}
