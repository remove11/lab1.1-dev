package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.RequestBodyData.ObservationCreate;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.entityDTO.ObservationDTO;
import kth.alex.demo.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class ObservationController {
    @Autowired
    private ObservationService observationService;

    @GetMapping("/observation")
    public List<ObservationDTO> sayHello() throws ServerErrorException {
        return observationService.getAll();
    }

    @GetMapping("/observation/{id}")
    public ResponseEntity<ObservationDTO> getObservationsById(@PathVariable String id) {
        System.out.println(id + " -----------------------");
        try {
            ObservationDTO observationDTO = observationService.findById(id);
            return ResponseEntity.ok(observationDTO);
        } catch (EntityNotFoundException | NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/observation")
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
