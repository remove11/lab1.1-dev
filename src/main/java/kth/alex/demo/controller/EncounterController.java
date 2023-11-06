package kth.alex.demo.controller;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.entityDTO.MessageDTO;
import kth.alex.demo.service.EncounterService;
import kth.alex.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EncounterController {
    @Autowired
    private EncounterService encounterService;

    @GetMapping("/encounter")
    public List<EncounterDTO> sayHello() {
        return encounterService.getAll();
    }

    @GetMapping("/encounter/{id}")
    public ResponseEntity<EncounterDTO> getEncounterById(@PathVariable Long id) {
        System.out.println(id + " -----------------------");
        try {
            EncounterDTO encounterDTO = encounterService.findById(id);
            return ResponseEntity.ok(encounterDTO); // Return 200 OK with the EncounterDTO
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if there's no encounter
        }
    }

}
