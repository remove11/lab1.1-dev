package kth.alex.demo.service;

import kth.alex.demo.entity.Person;
import kth.alex.demo.entityDTO.PatientDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PatientServiceTest {
    @Autowired
    PatientService patientService;

    @Test
    public void saveTest(){
        PatientDTO patientDTO = new PatientDTO(
                "666666",
                "Ta Quang",
                "Vo",
                "Borta",
                "911",
                Person.Gender.OTHER,
                "fdsf",
                LocalDateTime.now().minusYears(23)
        );
        PatientDTO p = patientService.save(patientDTO);
        System.out.println(p + "+++++++++++++++++++++");
    }
}