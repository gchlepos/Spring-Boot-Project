package com.hua.demo.dto;

public class PersonDataTransferObject {

    private String username;
    private String password;
    private int age;
    private String roles;
    private String residence;
    private String agency;

    public PersonDataTransferObject() {
    }

    public PersonDataTransferObject(String username, String password, int age, String roles, String residence, String agency) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.roles = roles;
        this.residence = residence;
        this.agency = agency;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        return "PersonDataTransferObject{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", roles='" + roles + '\'' +
                ", residence='" + residence + '\'' +
                ", agency='" + agency + '\'' +
                '}';
    }
}
