package com.hua.demo.controller;

import com.hua.demo.dto.PersonDataTransferObject;
import com.hua.demo.model.Appointment;
import com.hua.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class MainController {

    private final PersonService personService;

    @Autowired
    public MainController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(path = "api/citizen/cancelAppointment/{date}/{time}")
    public String cancelAppointment(@PathVariable("date") String date,
                                    @PathVariable(name = "time") String time) {
        Appointment appointment = personService.viewByDateAndTime(date, time);
        personService.cancel(appointment);
        return "cancelAppointment";
    }

    @PutMapping(path = "api/citizen/appointment/{oldDate}/{oldTime}/{newDate}/{newTime}")
    public void updateByDateAndTime(@PathVariable(name = "oldDate") String oldDate,
                                    @PathVariable(name = "oldTime") String oldTime,
                                    @PathVariable(name = "newDate") String newDate,
                                    @PathVariable(name = "newTime") String newTime) {
        personService.updateDateAndTime(oldDate, newDate, oldTime, newTime);
    }

    @GetMapping(path = "api/citizen/appointment/{date}/{time}")
    public Appointment viewByDateAndTime(@PathVariable(name = "date") String date,
                                         @PathVariable(name = "time") String time) {
        return personService.viewByDateAndTime(date, time);
    }

    @GetMapping(path = "api/citizen/appointment/{agency}")
    public List<Appointment> viewByAgency(@PathVariable(name = "agency") String agency) {
        return personService.viewByAgency(agency);
    }

    @PutMapping("api/employee/approve/{date}/{time}")
    public void approveAppointment(@PathVariable("date")String date, @PathVariable("time")String time){
        personService.approveAppointment(date, time);
    }

    @DeleteMapping("api/employee/cancel/{date}/{time}")
    public void declineAppointment(@PathVariable("date") String date, @PathVariable("time") String time) {
        personService.deleteAppointment(date, time);
    }


    @GetMapping("api/employee/citizens")
    public void viewCitizens() {
        personService.viewAllCitizens();
    }

    @GetMapping("api/employee/request")
    public String setRequest() {
        personService.setRequest();
        return "requestMade";
    }

    //approves a request(= creates a new agency and deletes this request)
    @GetMapping("api/admin/approve/{date}/{name}")
    public String approveRequest(@PathVariable("date") String date, @PathVariable("name") String name) {
        personService.acceptRequest(date, name);
        return "approveRequest";
    }

    //declines a request
    @GetMapping("api/admin/delete/{date}/{name}")
    public String declineRequest(@PathVariable("date") String date, @PathVariable("name") String name) {
        personService.declineRequest(date, name);
        return "declineRequest";
    }

    @DeleteMapping("api/admin/agencies/delete")
    public void deleteAllAgencies() {
        personService.deleteAllAgencies();
    }

    @GetMapping("/api/employee/viewAppointments")
    public String viewAppointments(Model model) {
        model.addAttribute("appointments", personService.viewAllAppointments());
        return "viewAppointments";
    }

    @GetMapping("/api/citizen/viewAppointments")
    public String viewMyAppointments(Model model) {
        model.addAttribute("appointments", personService.viewMyAppointments());
        return "viewMyAppointments";
    }

    @GetMapping("api/admin/requests/view")
    public String viewRequests(Model model) {
        model.addAttribute("requests", personService.viewRequests());
        return "viewRequests";
    }

    @GetMapping("api/admin/agencies/view")
    public String getAllAgencies(Model model) {
        model.addAttribute("agencies", personService.viewAgencies());
        return "viewAgencies";
    }

    @GetMapping("api/admin/employees/view")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", personService.viewAllEmployees());
        return "viewEmployees";
    }

    @GetMapping("api/employee/approveAppointment/{date}/{time}")
    public String approveAppointment(@PathVariable("date") String date,@PathVariable("time") String time, Model model) {
        personService.approveAppointment( date, time);
        return "approveAppointment";
    }

    @GetMapping("api/employee/declineAppointment/{date}/{time}")
    public String declineAppointment(@PathVariable("date") String date,@PathVariable("time") String time, Model model) {
        personService.deleteAppointment( date, time);
        return "declineAppointment";
    }

    @GetMapping("api/admin/viewMenu")
    public String viewMenuAdmin() {
        return "viewMenu";
    }

    @GetMapping("api/citizen/viewMenuCitizen")
    public String viewMenuCitizen() {
        return "viewMenuCitizen";
    }

    @GetMapping("api/employee/viewMenuEmployee")
    public String viewMenuEmployee() {
        return "viewMenuEmployee";
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        if(!personService.checkIfAdminExists()) {
            PersonDataTransferObject person = new PersonDataTransferObject();
            person.setUsername("ADMIN");
            person.setPassword("ADMIN");
            person.setAge(20);
            person.setRoles("ADMIN");
            person.setResidence("Athens");
//            System.out.println("been there done that");

            personService.register(person);
        }

    }
}
