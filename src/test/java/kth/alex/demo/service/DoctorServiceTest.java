package kth.alex.demo.service;

import kth.alex.demo.entity.Person;
import kth.alex.demo.entityDTO.DoctorDTO;
import kth.alex.demo.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DoctorServiceTest {
    @Autowired
    DoctorService doctorService;
    @Test
    public void saveTest(){
        DoctorDTO doctorDTO = new DoctorDTO(
                "1234566",
                "Alex",
                "Fredh",
                "Hemma",
                "112",
                Person.Gender.OTHER,
                "0",
                "2"
        );
        DoctorDTO d = doctorService.save(doctorDTO);
        System.out.println(d + "+****************");


    }

}