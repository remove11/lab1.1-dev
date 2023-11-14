package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.RequestBodyData.ObservationCreate;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.entityDTO.ObservationDTO;
import kth.alex.demo.repository.IdentityRepository;
import kth.alex.demo.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class ObservationController {
    @Autowired
    private ObservationService observationService;
    @Autowired
    private IdentityRepository identityRepository;

    @GetMapping("/observation")
    @PreAuthorize("hasRole('doctor')")
    public List<ObservationDTO> sayHello() throws ServerErrorException {
        return observationService.getAll();
    }

    @GetMapping("/observation/{id}")
    public ResponseEntity<ObservationDTO> getObservationsById(@PathVariable String id) {
        if(identityRepository.hasRole("patient") && !identityRepository.getUserId().equals(id) && !identityRepository.hasRole("doctor")){
            return ResponseEntity.status(403).build();
        }
        try {
            ObservationDTO observationDTO = observationService.findById(id);
            return ResponseEntity.ok(observationDTO);
        } catch (EntityNotFoundException | NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/observation/list/{id}")
    public ResponseEntity<List<ObservationDTO>> getListById(@PathVariable String id) {
        if(!identityRepository.getUserId().equals(id)){
            return ResponseEntity.status(403).build();
        }
        try {
            List<ObservationDTO> observationDTOs = observationService.findListById(id);
            return ResponseEntity.ok(Collections.singletonList((ObservationDTO) observationDTOs));
        } catch (EntityNotFoundException | NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/observation")
    @PreAuthorize("hasRole('doctor') || hasRole('otherPersonal')")
    public ResponseEntity<String> create(@RequestBody ObservationCreate observationCreate){
        try{
            observationService.create(observationCreate);
            return ResponseEntity.ok("Observation added");
        }catch (EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Something gone wrong");
        }
    }
}
