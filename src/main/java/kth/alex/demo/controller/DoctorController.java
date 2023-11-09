package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.entityDTO.DoctorDTO;
import kth.alex.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctor")
    public List<DoctorDTO> sayHello() {
        return doctorService.getAll();
    }

    @GetMapping("/doctor/{socialNr}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable String socialNr) {
        try {
            DoctorDTO doctorDTO = doctorService.getBySocial(socialNr);
            return ResponseEntity.ok(doctorDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
