package com.hua.demo.dto;

public class AppointmentDataTransferObject {

    private String date;
    private String time;
    private String place;
    private String agency;
    private String status;

    public AppointmentDataTransferObject() {
    }

    public AppointmentDataTransferObject(String date, String time, String place, String agency) {
        this.date = date;
        this.time = time;
        this.place = place;
        this.agency = agency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
