package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.EncounterCreate;
import kth.alex.demo.RequestBodyData.MedicalConditionCreate;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.entityDTO.MessageDTO;
import kth.alex.demo.service.EncounterService;
import kth.alex.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class EncounterController {
    @Autowired
    private EncounterService encounterService;

    @GetMapping("/encounter")
    public List<EncounterDTO> sayHello() throws ServerErrorException {
        return encounterService.getAll();
    }

    @GetMapping("/{personNr}/encounter")
    public ResponseEntity<List<EncounterDTO>> getAllUserEncounter (@PathVariable String personNr){
        List<EncounterDTO> encounters;
        try{
            encounters = encounterService.findByPersonNr(personNr);
            return ResponseEntity.ok(encounters);
        } catch (ServerErrorException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/encounter/{id}")
    public ResponseEntity<EncounterDTO> getEncounterById(@PathVariable String id) {
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

    @PostMapping("/encounter")
    public ResponseEntity<String> create(@RequestBody EncounterCreate encounterCreate){
        try{
            encounterService.create(encounterCreate);
            return ResponseEntity.ok("encounter added");
        }catch (EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Something gone wrong");
        }
    }
}

