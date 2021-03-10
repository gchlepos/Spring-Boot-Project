package com.hua.demo.repository;

import com.hua.demo.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

    Agency findFirstByAgencyName (String agencyName);
    //Agency findByAgencyName(String name);
    //void deleteAll();
}
