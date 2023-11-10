package kth.alex.demo.service;

import kth.alex.demo.RequestBodyData.MedicalConditionCreate;
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
        MedicalConditionCreate medicalConditionDTO = new MedicalConditionCreate(
                "122233",
                "32",
                "Riktigt Sjuk"
        );
        medicalConditionService.create(medicalConditionDTO);
    }
}