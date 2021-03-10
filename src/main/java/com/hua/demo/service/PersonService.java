package com.hua.demo.service;

import com.hua.demo.dto.AppointmentDataTransferObject;
import com.hua.demo.dto.PersonDataTransferObject;
import com.hua.demo.dto.RequestDataTransferObject;
import com.hua.demo.model.Agency;
import com.hua.demo.model.Appointment;
import com.hua.demo.model.Person;
import com.hua.demo.model.Request;

import java.util.List;


public interface PersonService {

    // ALL
    void register(PersonDataTransferObject personDataTransferObject); // check TODO: ALL

    // CITIZEN
    void set(AppointmentDataTransferObject appointmentDataTransferObject); // check TODO: CITIZEN
    void cancel(Appointment appointment); // check TODO: CITIZEN
    void updateDateAndTime(String oldDate, String newDate, String oldTime, String newTime); // check TODO: CITIZEN
    Appointment viewByDateAndTime(String date, String time); //check TODO: CITIZEN
    List<Appointment> viewByAgency(String agency); //check TODO: CITIZEN
    List<Appointment> viewMyAppointments();


    // EMPLOYEE
    List<Appointment> viewAllAppointments(); // TODO: EMPLOYEE
    void approveAppointment(String date, String time); // check TODO: EMPLOYEE
    void deleteAppointment(String date, String time); // check TODO: EMPLOYEE
    List<Person> viewAllCitizens(); // check TODO: EMPLOYEE
    void setRequest(); // TODO: EMPLOYEE


    // AGENT
    List<Request> viewRequests(); // TODO: AGENT
    void acceptRequest(String date, String name); // TODO: AGENT
    void declineRequest(String date, String name); // TODO: AGENT
    void deleteAllAgencies(); // TODO: AGENT
    List<Agency> viewAgencies(); // TODO: AGENT
    List<Person> viewAllEmployees();
    void createAgency(String agencyName);


    boolean checkIfAdminExists();
    Person loggedInUser();




}