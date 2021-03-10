package com.hua.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "place")
    private String place;

    @Column(name = "agency")
    private String agency;

    @Column(name = "status")
    private String status;

    @Column(name = "citizen_name")
    private String citizenName;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Citizen citizen;


    public Appointment(String date, String time, String place, String agency, String status) {
        this.date = date;
        this.time = time;
        this.place = place;
        this.agency = agency;
        this.status = status;
    }

    public Appointment() {

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

    public void setAgency(String state) {
        this.agency = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }
}
