package com.client.client.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppointmentDTOCheck {
    private Long id;
    private int year;
    private int month;
    private int day;
    private String hour;

    public AppointmentDTOCheck() {
    }

    public AppointmentDTOCheck(Long id, int year, int month, int day, String hour) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
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
}
