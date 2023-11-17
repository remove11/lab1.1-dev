package kth.alex.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.BadRequestException;
import kth.alex.demo.entity.Person;
import kth.alex.demo.entityDTO.UserInfoDTO;
import kth.alex.demo.repository.IdentityRepository;
import kth.alex.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
@SecurityRequirement(name="Keycloak")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    IdentityRepository identityRepository;
    @Autowired
    PersonRepository personRepository;



    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("")
    public ResponseEntity<UserInfoDTO> getUser() {
        List<String> roles = identityRepository.getRoles();
        String keycloakId = identityRepository.getUserId().orElseThrow(BadRequestException::new);
        Person person = personRepository.findByKeycloakId(keycloakId);

        if(roles == null || keycloakId == null || person ==null)
            return ResponseEntity.status(401).build();

        return ResponseEntity.ok(new UserInfoDTO(person.getSocialNr(), roles));
    }
}
