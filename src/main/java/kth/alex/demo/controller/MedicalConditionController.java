package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.MedicalConditionCreate;
import kth.alex.demo.entityDTO.MedicalConditionDTO;
import kth.alex.demo.service.MedicalConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class MedicalConditionController {
    @Autowired
    private MedicalConditionService medicalConditionService;

    @GetMapping("/medicalCondition")
    public List<MedicalConditionDTO> sayHello() throws ServerErrorException {
        return medicalConditionService.getAll();
    }

    @GetMapping("/medicalCondition/{id}")
    public ResponseEntity<MedicalConditionDTO> getMedicalConditionById(@PathVariable String id) {
        try {
            MedicalConditionDTO medicalConditionDTO = medicalConditionService.findById(id);
            return ResponseEntity.ok(medicalConditionDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{userSocial}/medicalCondition")
    public ResponseEntity<List<MedicalConditionDTO>> getMedicalConditionByPatientSocialNr(@PathVariable String userSocial) {
        try {
            List<MedicalConditionDTO> medicalConditionDTO = medicalConditionService.getAllByPatientSocialNr(userSocial);
            return ResponseEntity.ok(medicalConditionDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (ServerErrorException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/medicalCondition")
    public ResponseEntity<String> create(@RequestBody MedicalConditionCreate medicalConditionCreate){
        try{
            medicalConditionService.create(medicalConditionCreate);
            return ResponseEntity.ok("Entity added");
        }catch (EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Something gone wrong");
        }
    }
}
