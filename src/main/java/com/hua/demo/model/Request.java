package com.hua.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username")
    private String nameOfEmployee;

    @Column(name = "agencyName")
    private String agencyName;

    @Column(name = "agencyLocation")
    private String agencyLocation;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private String status;

    public Request() {
    }

    public Request(String nameOfEmployee, String agencyName, String agencyLocation, String date, String status) {
        this.nameOfEmployee = nameOfEmployee;
        this.agencyName = agencyName;
        this.agencyLocation = agencyLocation;
        this.date = date;
        this.status = status;
    }

    public String getNameOfEmployee() {
        return nameOfEmployee;
    }

    public void setNameOfEmployee(String nameOfEmployee) {
        this.nameOfEmployee = nameOfEmployee;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyLocation() {
        return agencyLocation;
    }

    public void setAgencyLocation(String agencyLocation) {
        this.agencyLocation = agencyLocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
