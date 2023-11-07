package kth.alex.demo.repository;

import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.OtherPersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherPersonalRepository extends JpaRepository<OtherPersonal, String> {
    OtherPersonal findBySocialNr(String socialNr);
}

