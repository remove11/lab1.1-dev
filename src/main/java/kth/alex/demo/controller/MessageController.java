package kth.alex.demo.controller;

import kth.alex.demo.entity.Message;
import kth.alex.demo.entityDTO.MessageDTO;
import kth.alex.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public List<MessageDTO> sayHello() {
        return messageService.getAll();
    }
}
