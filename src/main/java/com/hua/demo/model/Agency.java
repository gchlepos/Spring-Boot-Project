package com.hua.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "agencies")
public class Agency {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "agencyName")
    private String agencyName;

    @Column(name = "agencyLocation")
    private String agencyLocation;

    public Agency(String agencyName, String agencyLocation) {
        this.agencyName = agencyName;
        this.agencyLocation = agencyLocation;
    }

    public Agency() {
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
}
