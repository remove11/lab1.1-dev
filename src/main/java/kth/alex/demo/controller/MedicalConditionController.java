package kth.alex.demo.controller;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.entityDTO.MedicalConditionDTO;
import kth.alex.demo.service.MedicalConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicalConditionController {
    @Autowired
    private MedicalConditionService medicalConditionService;

    @GetMapping("/medicalCondition")
    public List<MedicalConditionDTO> sayHello() {
        return medicalConditionService.getAll();
    }

    @GetMapping("/medicalCondition/{id}")
    public ResponseEntity<MedicalConditionDTO> getMedicalConditionById(@PathVariable Long id) {
        System.out.println(id + " -----------------------");
        try {
            MedicalConditionDTO medicalConditionDTO = medicalConditionService.findById(id);
            return ResponseEntity.ok(medicalConditionDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
