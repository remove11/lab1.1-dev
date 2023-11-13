package kth.alex.demo.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.MessageCreate;
import kth.alex.demo.entityDTO.MessageDTO;
import kth.alex.demo.repository.IdentityRepository;
import kth.alex.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@SecurityRequirement(name="Keycloak")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private IdentityRepository identityRepository;

    @GetMapping("/message")
    @PreAuthorize("hasRole('admin')")
    public List<MessageDTO> sayHello() throws ServerErrorException {
        return messageService.getAll();
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<MessageDTO> getMssageById(@PathVariable String id) {
        if(!identityRepository.getUserId().equals(id)){
            return ResponseEntity.status(403).build();
        }
        try {
            MessageDTO messageDTO = messageService.findById(id);
            return ResponseEntity.ok(messageDTO);
        } catch (EntityNotFoundException | NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/message/list/{id}")
    public ResponseEntity<List<MessageDTO>> getListById(@PathVariable String id) {
        if(!identityRepository.getUserId().equals(id)){
            return ResponseEntity.status(403).build();
        }
        try {
            List<MessageDTO> messageDTO = messageService.findListById(id);
            return ResponseEntity.ok(Collections.singletonList((MessageDTO) messageDTO));
        } catch (EntityNotFoundException | NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/message")
    public ResponseEntity<String> create(@RequestBody MessageCreate messageCreate){
        try{
            messageService.create(messageCreate);
            return ResponseEntity.ok("Message sent");
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
}