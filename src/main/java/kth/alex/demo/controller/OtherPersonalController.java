package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import kth.alex.demo.entityDTO.OtherPersonalDTO;
import kth.alex.demo.service.OtherPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class OtherPersonalController {
    @Autowired
    private OtherPersonalService otherPersonalService;

    @GetMapping("/otherPersonal")
    public List<OtherPersonalDTO> sayHello() throws NotFoundException, ServerErrorException {
        return otherPersonalService.getAll();
    }

    @GetMapping("/otherPersonal/{socialNr}")
    public ResponseEntity<OtherPersonalDTO> getOtherById(@PathVariable String socialNr) {
        System.out.println(socialNr + " -----------------------");
        try {
            OtherPersonalDTO otherPersonalDTO = otherPersonalService.getBySocial(socialNr);
            return ResponseEntity.ok(otherPersonalDTO);
        } catch (NotFoundException | ServerErrorException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/otherPersonal")
    public ResponseEntity<String> create(@RequestBody UserCreationRequest userCreationRequest){
        try{
            otherPersonalService.save(userCreationRequest);
            return ResponseEntity.ok("Doctor created");
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
}
