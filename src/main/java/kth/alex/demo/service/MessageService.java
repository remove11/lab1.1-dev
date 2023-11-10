package kth.alex.demo.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kth.alex.demo.RequestBodyData.MedicalConditionCreate;
import kth.alex.demo.RequestBodyData.MessageCreate;
import kth.alex.demo.entity.*;
import kth.alex.demo.entityDTO.MessageDTO;
import kth.alex.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    IdentityRepository identityRepository;

    public List<MessageDTO> getAll() {
        List<Message> messages = messageRepository.findAll();

        List<MessageDTO> messageDTOs = new ArrayList<>();
        for (Message m : messages) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setId(m.getId());
            messageDTO.setContent(m.getContent());
            messageDTO.setCreatedAt(m.getCreatedAt());
            messageDTO.setReceiverName("receiver");
            messageDTO.setSenderName("sender");
            messageDTOs.add(messageDTO);
        }
        return messageDTOs;
    }

    public MessageDTO findById(String id) {
        return messageRepository.findById(id)
                .map(m -> {
                    MessageDTO messageDTO = new MessageDTO();

                    messageDTO.setId(m.getId());
                    messageDTO.setSenderSocialNr(m.getSender().getSocialNr());
                    messageDTO.setReceiverSocialNr(m.getReceiver().getSocialNr());
                    messageDTO.setContent(m.getContent());
                    messageDTO.setCreatedAt(m.getCreatedAt());

                    return messageDTO;
                })
                .orElseThrow(() -> new EntityNotFoundException("Encounter not found with id " + id));
    }

    @Transactional
    public void create(MessageCreate messageCreate){
        Message message = new Message();

        String senderId = identityRepository.getUserId().orElseThrow();

        Person sender = personRepository.findBySocialNr(senderId);
        Person reciver = personRepository.findBySocialNr(messageCreate.getReceiverSocialNr());

        message.setSender(sender);
        message.setReceiver(reciver);
        message.setContent(message.getContent());

        messageRepository.save(message);
    }

}

