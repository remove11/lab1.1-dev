package kth.alex.demo.service;
import kth.alex.demo.entity.*;
import kth.alex.demo.entityDTO.MessageDTO;
import kth.alex.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<MessageDTO> getAll() {
        List<Message> messages = messageRepository.findAll();

        List<MessageDTO> messageDTOs = new ArrayList<>();
        for (Message m : messages) {
            MessageDTO messageDTO = new MessageDTO();

            messageDTO.setId(m.getId());
            messageDTO.setContent(m.getContent());
            messageDTO.setCreatedAt(m.getCreatedAt());
            //messageDTO.setReceiverName("receiver");
            //messageDTO.setSenderName("sender");

            messageDTOs.add(messageDTO);
        }
        return messageDTOs;
    }
}

