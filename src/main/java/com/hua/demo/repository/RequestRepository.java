package com.hua.demo.repository;

import com.hua.demo.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAll();
    Request findByAgencyName( String agencyName);
    Request findByDateAndAgencyName(String date, String name);
}
