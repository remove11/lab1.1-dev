package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.MedicalConditionCreate;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.entityDTO.MedicalConditionDTO;
import kth.alex.demo.repository.IdentityRepository;
import kth.alex.demo.service.MedicalConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class MedicalConditionController {
    @Autowired
    private MedicalConditionService medicalConditionService;
    @Autowired
    private IdentityRepository identityRepository;

    @GetMapping("/medicalCondition")
    @PreAuthorize("hasRole('doctor')")
    public List<MedicalConditionDTO> sayHello() throws ServerErrorException {
        return medicalConditionService.getAll();
    }

    @GetMapping("/medicalCondition/{id}")
    public ResponseEntity<MedicalConditionDTO> getMedicalConditionById(@PathVariable String id) {
        if(identityRepository.hasRole("patient") && !identityRepository.getUserId().equals(id) && !identityRepository.hasRole("doctor")){
            return ResponseEntity.status(403).build();
        }
        try {
            MedicalConditionDTO medicalConditionDTO = medicalConditionService.findById(id);
            return ResponseEntity.ok(medicalConditionDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/medicalCondition/list/{id}")
    public ResponseEntity<List<MedicalConditionDTO>> getListById(@PathVariable String id) {
        if(!identityRepository.getUserId().equals(id)){
            return ResponseEntity.status(403).build();
        }
        try {
            List<MedicalConditionDTO> medicalConditionDTOs = medicalConditionService.findListById(id);
            return ResponseEntity.ok(Collections.singletonList((MedicalConditionDTO) medicalConditionDTOs));
        } catch (EntityNotFoundException | NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/medicalCondition")
    @PreAuthorize("hasRole('doctor')")
    public ResponseEntity<String> create(@RequestBody MedicalConditionCreate medicalConditionCreate){
        try{
            medicalConditionService.create(medicalConditionCreate);
            return ResponseEntity.ok("MedicalCondition added");
        }catch (EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Something gone wrong");
        }
    }
}
