package com.hua.demo.controller;

import com.hua.demo.dto.AppointmentDataTransferObject;
import com.hua.demo.dto.PersonDataTransferObject;
import com.hua.demo.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/citizen/appointment")
class AppointmentRegistrationController {

    private final PersonService personService;

    public AppointmentRegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @ModelAttribute("appointment")
    public AppointmentDataTransferObject appointmentDataTransferObject() {
        return new AppointmentDataTransferObject();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "appointmentRegistration";
    }

    @PostMapping
    public String registerAppointment(@ModelAttribute("appointment") AppointmentDataTransferObject appointmentDataTransferObject) {
        personService.set(appointmentDataTransferObject);
        return "redirect:/api/citizen/appointment?success";
    }
}
