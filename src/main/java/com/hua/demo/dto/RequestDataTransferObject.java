package com.hua.demo.dto;

public class RequestDataTransferObject {

    private String nameOfEmployee;
    private String agencyName;
    private String agencyLocation;
    private String date;
    private String status;

    public RequestDataTransferObject(String nameOfEmployee,
                                     String agencyName,
                                     String agencyLocation,
                                     String date, String status) {
        this.nameOfEmployee = nameOfEmployee;
        this.agencyName = agencyName;
        this.agencyLocation = agencyLocation;
        this.date = date;
        this.status = status;
    }

    public RequestDataTransferObject() {
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
