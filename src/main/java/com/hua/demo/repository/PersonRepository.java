package com.hua.demo.repository;

import com.hua.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

//    List<Citizen> findAll();
//    Citizen findByEmail(String email);
    Person findByUsername(String firstName);
    Person findByAgency( String agency);
    List<Person> findByRoles(String role);
    Person findFirstByRoles( String role);
}
