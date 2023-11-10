package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.entityDTO.MedicalConditionDTO;
import kth.alex.demo.entityDTO.MessageDTO;
import kth.alex.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public List<MessageDTO> sayHello() {
        return messageService.getAll();
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<MessageDTO> getMssageById(@PathVariable String id) {
        System.out.println(id + " -----------------------");
        try {
            MessageDTO messageDTO = messageService.findById(id);
            return ResponseEntity.ok(messageDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}