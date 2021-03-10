package com.hua.demo.repository;

import com.hua.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    //Appointment findById(String id);
    Appointment findByDateAndTime(String date, String time);
    List<Appointment> findAll();
    List<Appointment> findByAgency(String agency);
    List<Appointment> findByCitizenName(String citizenName);
}