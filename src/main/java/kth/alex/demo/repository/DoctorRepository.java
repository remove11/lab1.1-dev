package kth.alex.demo.repository;

import kth.alex.demo.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    @Query("SELECT o FROM Doctor o WHERE o.socialNr = :socialNr")
    Doctor findBySocialNr(String socialNr);
    Doctor findByEmployeeId(String employeeId);
}

