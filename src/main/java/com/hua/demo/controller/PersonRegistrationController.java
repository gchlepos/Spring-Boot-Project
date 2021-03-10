package com.hua.demo.controller;

import com.hua.demo.dto.PersonDataTransferObject;
import com.hua.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
class PersonRegistrationController {

    private final PersonService personService;

    public PersonRegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @ModelAttribute("person")
    public PersonDataTransferObject personDataTransferObject() {
        return new PersonDataTransferObject();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String personRegistrationAccount(@ModelAttribute("person") PersonDataTransferObject personDataTransferObject) {
        personService.register(personDataTransferObject);
        return "redirect:/registration?success";
    }
}
