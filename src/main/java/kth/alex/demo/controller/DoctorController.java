package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import kth.alex.demo.entityDTO.DoctorDTO;
import kth.alex.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctor")
    public List<DoctorDTO> sayHello() throws ServerErrorException {
        return doctorService.getAll();
    }

    @GetMapping("/doctor/{socialNr}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable String socialNr) {
        try {
            DoctorDTO doctorDTO = doctorService.getBySocial(socialNr);
            return ResponseEntity.ok(doctorDTO);
        } catch (EntityNotFoundException | ServerErrorException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/doctor")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> create(@RequestBody UserCreationRequest userCreationRequest){
        try{
            doctorService.save(userCreationRequest);
            return ResponseEntity.ok("Doctor created");
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

}
