package com.asignment3.asignment3.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue
    private Long id;
    private int year;
    private int month;
    private int day;
    private String hour;

    @ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    private Patient patient;

    public Appointment() {
    }

    public Appointment(@JsonProperty("id") Long id,
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
        this.doctor = new Doctor();
        doctor.setId(doctor_id);
        this.patient = new Patient();
        patient.setId(patient_id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() { return day; }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public String getHour() {
        return hour;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour='" + hour + '\'' +
                '}';
    }
}
