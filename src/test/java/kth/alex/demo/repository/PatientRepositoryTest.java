package kth.alex.demo.repository;

import kth.alex.demo.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientRepositoryTest {
    @Autowired
    PatientRepository patientRepository;

    @Test
    public void findBySocialNrTest(){
        Patient patient = patientRepository.findBySocialNr("122233");
        System.out.println(patient.getSocialNr());
        System.out.println(patient.getSurename());
    }
}