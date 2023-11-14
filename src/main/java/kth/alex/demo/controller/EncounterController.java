package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.RequestBodyData.EncounterCreate;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.entityDTO.MessageDTO;
import kth.alex.demo.repository.IdentityRepository;
import kth.alex.demo.service.EncounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class EncounterController {
    @Autowired
    private EncounterService encounterService;

    @Autowired
    private IdentityRepository identityRepository;

    @GetMapping("/encounter")
    @PreAuthorize("hasRole('doctor')")
    public List<EncounterDTO> sayHello() throws ServerErrorException {
        return encounterService.getAll();
    }

    @GetMapping("/encounter/{id}")
    public ResponseEntity<EncounterDTO> getEncounterById(@PathVariable String id) {

        if(identityRepository.hasRole("patient") && !identityRepository.getUserId().equals(id) && !identityRepository.hasRole("doctor")){
            return ResponseEntity.status(403).build();
        }

        try {
            EncounterDTO encounterDTO = encounterService.findById(id);
            return ResponseEntity.ok(encounterDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }catch (Exception ex){
            ResponseEntity.internalServerError().body("ex");
            return null;
        }
    }

    @GetMapping("/encounter/list/{id}")
    public ResponseEntity<List<EncounterDTO>> getListById(@PathVariable String id) {
        if(!identityRepository.getUserId().equals(id)){
            return ResponseEntity.status(403).build();
        }
        try {
            List<EncounterDTO> encounterDTOs = encounterService.findListById(id);
            return ResponseEntity.ok(Collections.singletonList((EncounterDTO) encounterDTOs));
        } catch (EntityNotFoundException | NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/encounter")
    @PreAuthorize("hasRole('doctor') || hasRole('otherPersonal')")
    public ResponseEntity<String> create(@RequestBody EncounterCreate encounterCreate){
        try{
            encounterService.create(encounterCreate);
            return ResponseEntity.ok("Encounter added");
        }catch (EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Something gone wrong");
        }
    }
}

