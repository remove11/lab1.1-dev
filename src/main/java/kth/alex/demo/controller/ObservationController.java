package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.entityDTO.ObservationDTO;
import kth.alex.demo.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class ObservationController {
    @Autowired
    private ObservationService observationService;

    @GetMapping("/observation")
    public List<ObservationDTO> sayHello() {
        return observationService.getAll();
    }

    @GetMapping("/observation/{id}")
    public ResponseEntity<ObservationDTO> getObservationsById(@PathVariable Long id) {
        System.out.println(id + " -----------------------");
        try {
            ObservationDTO observationDTO = observationService.findById(id);
            return ResponseEntity.ok(observationDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
