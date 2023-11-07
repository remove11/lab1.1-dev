package kth.alex.demo.repository;

import kth.alex.demo.entity.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DoctorRepositoryTest {
    @Autowired
    DoctorRepository doctorRepository;

    @Test
    public void findBySocialNrTest(){
        Doctor doctor = doctorRepository.findBySocialNr("3333334");
        System.out.println("-----------------------");
        System.out.println(doctor.getSocialNr());
        System.out.println(doctor.getSurename());
        System.out.println("-----------------------");
    }
}