package kth.alex.demo.service;

import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.entity.Encounter;
import kth.alex.demo.entity.Message;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.entityDTO.MessageDTO;
import kth.alex.demo.repository.EncounterRepository;
import kth.alex.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EncounterService {
    @Autowired
    private EncounterRepository encounterRepository;

    public List<EncounterDTO> getAll() {
        List<Encounter> encounters = encounterRepository.findAll();

        List<EncounterDTO> encounterDTOs = new ArrayList<>();
        for (Encounter e : encounters) {
            EncounterDTO encounterDTO = new EncounterDTO();

            encounterDTO.setId(e.getId());
            encounterDTO.setDescription(e.getDescription());
            encounterDTO.setCreatedAt(e.getCreatedAt());

            encounterDTOs.add(encounterDTO);
        }
        return encounterDTOs;
    }



    public EncounterDTO findById(Long id) {
        return encounterRepository.findById(id)
                .map(e -> {
                    System.out.println("hej--------------");
                    //System.out.println(e);
                    System.out.println("--------------");

                    EncounterDTO encounterDTO = new EncounterDTO();
                    encounterDTO.setId(e.getId());
                    //encounterDTO.setCreatedBy(e.getCreatedBy());
                   // encounterDTO.setPatient(e.getPatient());
                    encounterDTO.setDescription(e.getDescription());
                    encounterDTO.setCreatedAt(e.getCreatedAt());
                    return encounterDTO;
                })
                .orElseThrow(() -> new EntityNotFoundException("Encounter not found with id " + id));
    }
}

