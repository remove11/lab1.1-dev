package kth.alex.demo.service;
import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.entity.*;
import kth.alex.demo.entityDTO.MedicalConditionDTO;
import kth.alex.demo.entityDTO.MessageDTO;
import kth.alex.demo.repository.DoctorRepository;
import kth.alex.demo.repository.MessageRepository;
import kth.alex.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

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
                    System.out.println("*************");
                    messageDTO.setSenderSocialNr(m.getSender().getSocialNr());
                    messageDTO.setReceiverSocialNr(m.getReceiver().getSocialNr());
                    messageDTO.setContent(m.getContent());
                    messageDTO.setCreatedAt(m.getCreatedAt());
                    return messageDTO;
                })
                .orElseThrow(() -> new EntityNotFoundException("Encounter not found with id " + id));
    }
}

