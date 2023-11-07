package kth.alex.demo.repository;
import kth.alex.demo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    Patient findBySocialNr(String socialNr);
}

