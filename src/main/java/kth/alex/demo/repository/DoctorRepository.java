package kth.alex.demo.repository;

import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Doctor findBySocialNr(String socialNr);
    Doctor findByEmployeeId(String employeeId);
    Doctor findByKeycloakId(String keycloakId);
}

