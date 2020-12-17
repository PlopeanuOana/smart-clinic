package com.client.client.DTO.map;


public class AppointmentDTOM extends NameIdDTO {

    private String speciality;
    private String medical_history;
    private String hour;
    private int day;
    private int month;
    private int year;

    public AppointmentDTOM() {
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getMedical_history() {
        return medical_history;
    }

    public void setMedical_history(String medical_history) {
        this.medical_history = medical_history;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public String getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }
}
