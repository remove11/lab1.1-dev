package kth.alex.demo.repository;

import kth.alex.demo.entity.Encounter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EncounterRepositoryTest {
    @Autowired
    EncounterRepository encounterRepository;

    @Test
    public void findByIdTest(){
        Encounter encounter = encounterRepository.findById(1L).orElse(null);

        System.out.println("--------------------------");
            System.out.println(encounter.getDescription());
            System.out.println(encounter.getPatient().getLastname());
            System.out.println(encounter.getPatient().getSurename());
        System.out.println("--------------------------");
    }
}