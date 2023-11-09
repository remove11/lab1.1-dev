package kth.alex.demo.service;

import kth.alex.demo.entityDTO.MedicalConditionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
@SpringBootTest
class MedicalConditionServiceTest {
    @Autowired
    MedicalConditionService medicalConditionService;

    @Test
    void create() {
        MedicalConditionDTO medicalConditionDTO = new MedicalConditionDTO(
                2L,
                "122233",
                "surename",
                "32",
                "surename1",
                "Riktigt Sjuk",
                LocalDateTime.now()
        );
        medicalConditionService.create(medicalConditionDTO);
    }
}