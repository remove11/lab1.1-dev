package kth.alex.demo.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
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

    public List<MessageDTO> getAll() throws ServerErrorException {
        List<Message> messages;
        try {
            messages = messageRepository.findAll();
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

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

    public MessageDTO findById(String id) throws NotFoundException {
        Message m = messageRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Message not found"));
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setId(m.getId());
        messageDTO.setSenderSocialNr(m.getSender().getSocialNr());
        messageDTO.setReceiverSocialNr(m.getReceiver().getSocialNr());
        messageDTO.setContent(m.getContent());
        messageDTO.setCreatedAt(m.getCreatedAt());

        return messageDTO;
    }

    @Transactional
    public void create(MessageCreate messageCreate) throws ServerErrorException, NotFoundException {
        Message message = new Message();
        message.setContent(messageCreate.getDescription());

        String keycloakId = identityRepository
                .getUserId()
                .orElseThrow(() -> new NotFoundException("User Not found"));

        Person sender;
        Person reciver;

        try {
            sender = personRepository.findByKeycloakId(keycloakId);
            reciver = personRepository.findBySocialNr(messageCreate.getReceiverSocialNr());
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

        if(sender == null){
            throw new NotFoundException("Sender not found");
        }
        if(reciver == null){
            throw new NotFoundException("Receiver not found");
        }

        message.setSender(sender);
        message.setReceiver(reciver);
        message.setContent(message.getContent());

        try{
            messageRepository.save(message);
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }
    }

    public List<MessageDTO> getConversation(String p1, String p2) throws ServerErrorException {
        List<Message> messages;
        try {
            System.out.println("----------------1");
            messages = messageRepository.findConversation(p1, p2);
            System.out.println("----------------2");
        }catch (Exception ex){
            System.out.println("----------------3");
            System.out.println(ex);
            System.out.println(ex.getMessage());
            throw new ServerErrorException(ex.getMessage());
        }

        List<MessageDTO> messageDTOs = new ArrayList<>();
        for (Message m : messages) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setId(m.getId());
            messageDTO.setContent(m.getContent());
            messageDTO.setCreatedAt(m.getCreatedAt());
            messageDTO.setReceiverName(m.getReceiver().getLastname() + " " + m.getReceiver().getSurename());
            messageDTO.setReceiverSocialNr(m.getReceiver().getSocialNr());
            messageDTO.setSenderName(m.getSender().getLastname() + " " + m.getSender().getSurename());
            messageDTO.setSenderSocialNr(m.getSender().getSocialNr());
            messageDTOs.add(messageDTO);
        }
        return messageDTOs;
    }
}