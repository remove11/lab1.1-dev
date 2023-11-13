package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import kth.alex.demo.entityDTO.PatientDTO;
import kth.alex.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/patient")
    @PreAuthorize("hasRole('doctor') || hasRole('otherPersonal')")
    public List<PatientDTO> sayHello() throws ServerErrorException {
        return patientService.getAll();
    }

    @GetMapping("/patient/{socialNr}")
    @PreAuthorize("hasRole('doctor') || hasRole('otherPersonal')")
    public ResponseEntity<PatientDTO> getOtherById(@PathVariable String socialNr) {
        try {
            PatientDTO patientDTO = patientService.getBySocial(socialNr);
            return ResponseEntity.ok(patientDTO);
        }  catch (NotFoundException | ServerErrorException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/patient")
    @PreAuthorize("hasRole('doctor') || hasRole('otherPersonal')")
    public ResponseEntity<String> create(@RequestBody UserCreationRequest userCreationRequest){
        try{
            patientService.save(userCreationRequest);
            return ResponseEntity.ok("Patient created");
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
}
