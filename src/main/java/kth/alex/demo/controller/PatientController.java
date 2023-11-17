package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import kth.alex.demo.entityDTO.PatientDTO;
import kth.alex.demo.repository.IdentityRepository;
import kth.alex.demo.repository.KeycloakRepository;
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
    @Autowired
    private IdentityRepository identityRepository;
    @Autowired
    private KeycloakRepository keycloakRepository;

    @GetMapping("/patient")
    public List<PatientDTO> sayHello() throws ServerErrorException {
        return patientService.getAll();
    }

    @GetMapping("/patient/{socialNr}")
    public ResponseEntity<PatientDTO> getOtherById(@PathVariable String socialNr) {
        System.out.println(socialNr + " -----------------------");
        try {
            PatientDTO patientDTO = patientService.getBySocial(socialNr);

            return ResponseEntity.ok(patientDTO);
        }  catch (NotFoundException | ServerErrorException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/patient")
    public ResponseEntity<String> create(@RequestBody UserCreationRequest userCreationRequest){
        System.out.println("inside create patient");
        try{
            patientService.save(userCreationRequest);
            return ResponseEntity.ok("Doctor created");
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
}
