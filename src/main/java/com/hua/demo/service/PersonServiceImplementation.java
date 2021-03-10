package com.hua.demo.service;

import com.hua.demo.dto.AppointmentDataTransferObject;
import com.hua.demo.dto.PersonDataTransferObject;
import com.hua.demo.dto.RequestDataTransferObject;
import com.hua.demo.model.Agency;
import com.hua.demo.model.Appointment;
import com.hua.demo.model.Person;
import com.hua.demo.model.Request;
import com.hua.demo.repository.AgencyRepository;
import com.hua.demo.repository.AppointmentRepository;
import com.hua.demo.repository.PersonRepository;
import com.hua.demo.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImplementation implements PersonService {

    private final PersonRepository personRepository;
    private final AppointmentRepository appointmentRepository;
    private final RequestRepository requestRepository;
    private final AgencyRepository agencyRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImplementation(PersonRepository personRepository,
                                       AppointmentRepository appointmentRepository,
                                       RequestRepository requestRepository,
                                       AgencyRepository agencyRepository,
                                       BCryptPasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.appointmentRepository = appointmentRepository;
        this.requestRepository = requestRepository;
        this.agencyRepository = agencyRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void register(PersonDataTransferObject personDataTransferObject) {

        if (personDataTransferObject.getRoles().equals("ADMIN")) {
            Person person = new Person(personDataTransferObject.getUsername(),
                    passwordEncoder.encode(personDataTransferObject.getPassword()),
                    personDataTransferObject.getAge(),
                    personDataTransferObject.getRoles(),
                    personDataTransferObject.getResidence(),
                    personDataTransferObject.getAgency()
            );

            personRepository.save(person);

            return;
        }

        if (!personDataTransferObject.getRoles().equals("EMPLOYEE,")) {
            personDataTransferObject.setRoles("CITIZEN");
        } else {
            if (personDataTransferObject.getAgency().isBlank()){
                return;
            }
            personDataTransferObject.setRoles("EMPLOYEE");
        }

        Person person = new Person(personDataTransferObject.getUsername(),
                passwordEncoder.encode(personDataTransferObject.getPassword()),
                personDataTransferObject.getAge(),
                personDataTransferObject.getRoles(),
                personDataTransferObject.getResidence(),
                personDataTransferObject.getAgency()
                );

//        if (person.getRoles().equals("EMPLOYEE")) {
//            if (!checkIfAgencyExists(person.getAgency())) {
//
//                person.setActive(0); // makes him inactive until agency is confirmed
//                personRepository.save(person);
//
//                //return "Go to localhost:8080/api/createAgency to create this agency!";
//            }
//        }

        if(person.getRoles().equals("CITIZEN")) {
            person.setAgency(null);
        }

        personRepository.save(person);
        //return "Registered";
    }

    @Override
    public void set(AppointmentDataTransferObject appointmentDataTransferObject) {
        Appointment newAppointment = new Appointment(appointmentDataTransferObject.getDate(),
                appointmentDataTransferObject.getTime(),
                appointmentDataTransferObject.getPlace(),
                appointmentDataTransferObject.getAgency(),
                "pending");

        Person person = loggedInUser();

        newAppointment.setCitizenName(person.getUsername());

        List<Appointment> existingAppointments = appointmentRepository.findByCitizenName( newAppointment.getCitizenName());

        Iterator<Appointment> itr = existingAppointments.iterator();
        while(itr.hasNext()) {

            Appointment thisAppointment = itr.next();
            if (thisAppointment.getDate().equals(newAppointment.getDate()) &&
                    thisAppointment.getTime().equals(newAppointment.getTime())) {
                //System.out.println("not today biatch");
                return ;
            }

        }

        Optional<Agency> agency = Optional.ofNullable(agencyRepository.findFirstByAgencyName(newAppointment.getAgency()));

        if (agency.isEmpty()) {
            return;
        }

        appointmentRepository.save(newAppointment);
    }


    @Override
    public void cancel(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }

    @Override
    public void updateDateAndTime(String oldDate, String newDate, String oldTime, String newTime) {
        Appointment oldAppointment = appointmentRepository.findByDateAndTime(oldDate, oldTime);
        if (newDate == null) {
            oldAppointment.setTime(newTime);
        } else if (newTime == null) {
            oldAppointment.setDate(newDate);
        } else {
            oldAppointment.setTime(newTime);
            oldAppointment.setDate(newDate);
        }
        appointmentRepository.save(oldAppointment);
    }

    @Override
    public Appointment viewByDateAndTime(String date, String time) {
        return appointmentRepository.findByDateAndTime(date, time);
    }

    @Override
    public List<Appointment> viewByAgency(String agency) {
        return appointmentRepository.findByAgency(agency);
    }

    @Override
    public List<Appointment> viewAllAppointments() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Person person = personRepository.findByUsername(username);

        String agency = person.getAgency();

        return  appointmentRepository.findByAgency( agency);

    }

    @Override
    public void approveAppointment(String date, String time) {
        Appointment appointment = appointmentRepository.findByDateAndTime(date, time);
        appointment.setStatus("approved");
        appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(String date, String time) {
        Appointment appointment = appointmentRepository.findByDateAndTime(date, time);
        appointment.setStatus("canceled");
        appointmentRepository.save(appointment);
    }

    @Override
    public List<Person> viewAllCitizens() {
        return personRepository.findAll();
    }

    @Override
    public void setRequest() {

        //checks if agency already exists
        Optional<Agency> agency = Optional.ofNullable(agencyRepository.findFirstByAgencyName(loggedInUser().getAgency()));

        Optional<Request> agency1 = Optional.ofNullable(requestRepository.findByAgencyName(loggedInUser().getAgency()));

        if ( agency.isPresent() || agency1.isPresent()) {
            System.out.println( "bitch am bussin");
            return;
        }

        Optional<Person> person = Optional.ofNullable(personRepository.findByUsername(loggedInUser().getUsername()));

        if (person.isEmpty()) {
            System.out.println("Sb is trying to hack us");
        } else {
            Request request = new Request(person.get().getUsername(),
                    person.get().getAgency(),
                    person.get().getResidence(),
                    java.time.LocalDate.now().toString(),
                    "pending");
            requestRepository.save(request);
        }
    }

    @Override
    public List<Agency> viewAgencies() {
        //System.out.println("Get all Agencies request executed");
        return agencyRepository.findAll();
    }

    @Override
    public void deleteAllAgencies() {
        //System.out.println("Delete all agencies request executed");
        agencyRepository.deleteAll();
        //return "All agencies deleted";
    }


    @Override
    public List<Request> viewRequests() {
        //System.out.println("Get all Requests request executed");
        return requestRepository.findAll();
    }

    @Override
    public void declineRequest(String date, String name) {
        Request request = requestRepository.findByDateAndAgencyName(date, name);
        request.setStatus("declined");
        requestRepository.save(request);

    }

    @Override
    public void acceptRequest(String date, String name) {
        System.out.println(date);
        Request request = requestRepository.findByDateAndAgencyName(date, name);
        System.out.println(request.getAgencyName());
        request.setStatus("accepted");

        Optional<Agency> agency = Optional.ofNullable(agencyRepository.findFirstByAgencyName(request.getAgencyName()));

        if (agency.isEmpty()) {
            System.out.println("New agency");
            createAgency(request.getAgencyName());
        }

        Person person = personRepository.findByAgency(request.getAgencyName());
        person.setActive(1); //makes the employee active
        personRepository.save(person);

        requestRepository.save(request);
    }

    private boolean checkIfAgencyExists( String agencyName) {

        Optional<Agency> agency = Optional.ofNullable(agencyRepository.findFirstByAgencyName(agencyName));

        return agency.isPresent();

    }

    @Override
    public boolean checkIfAdminExists() {

        Optional<Person> admin = Optional.ofNullable(personRepository.findFirstByRoles("ADMIN"));

        return admin.isPresent();

    }

    @Override
    public void createAgency( String agencyName) {
        Request request = requestRepository.findByAgencyName( agencyName);

        Agency agency = new Agency(
                request.getAgencyName(),
                request.getAgencyLocation());

        agencyRepository.save(agency);

    }

    @Override
    public Person loggedInUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return personRepository.findByUsername(username);

    }

    @Override
    public List<Appointment> viewMyAppointments() {

        Person person = loggedInUser();

        return  appointmentRepository.findByCitizenName(person.getUsername());

        //return appointmentRepository.findByCitizenName( citizenName);
    }

    @Override
    public List<Person> viewAllEmployees() {
        return personRepository.findByRoles("EMPLOYEE");
    }
}
