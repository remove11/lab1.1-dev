package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import kth.alex.demo.entityDTO.PatientDTO;
import kth.alex.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/patient")
    public List<PatientDTO> sayHello() {
        return patientService.getAll();
    }

    @GetMapping("/patient/{socialNr}")
    public ResponseEntity<PatientDTO> getOtherById(@PathVariable String socialNr) {
        System.out.println(socialNr + " -----------------------");
        try {
            PatientDTO patientDTO = patientService.getBySocial(socialNr);
            return ResponseEntity.ok(patientDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/patient")
    public ResponseEntity<String> create(@RequestBody UserCreationRequest userCreationRequest){
        try{
            patientService.save(userCreationRequest);
            return ResponseEntity.ok("Doctor created");
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
}
