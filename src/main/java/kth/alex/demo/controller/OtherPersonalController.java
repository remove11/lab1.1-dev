package kth.alex.demo.controller;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.entityDTO.OtherPersonalDTO;
import kth.alex.demo.service.OtherPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OtherPersonalController {
    @Autowired
    private OtherPersonalService otherPersonalService;

    @GetMapping("/otherPersonal")
    public List<OtherPersonalDTO> sayHello() {
        return otherPersonalService.getAll();
    }

    @GetMapping("/otherPersonal/{socialNr}")
    public ResponseEntity<OtherPersonalDTO> getEncounterById(@PathVariable String socialNr) {
        System.out.println(socialNr + " -----------------------");
        try {
            OtherPersonalDTO otherPersonalDTO = otherPersonalService.getBySocial(socialNr);
            return ResponseEntity.ok(otherPersonalDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
