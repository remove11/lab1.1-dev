package kth.alex.demo.service;

import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.entity.Encounter;
import kth.alex.demo.entity.Observation;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.entityDTO.ObservationDTO;
import kth.alex.demo.repository.EncounterRepository;
import kth.alex.demo.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObservationService {
    @Autowired
    private ObservationRepository observationRepository;

    public List<ObservationDTO> getAll() {
        List<Observation> observations = observationRepository.findAll();

        List<ObservationDTO> observationDTOs = new ArrayList<>();
        for (Observation o : observations) {
            ObservationDTO observationDTO = new ObservationDTO();
            observationDTO.setId(o.getId());
            observationDTO.setDescription(o.getDescription());
            observationDTO.setCreatedAt(o.getCreatedAt());

            observationDTOs.add(observationDTO);
        }
        return observationDTOs;
    }



    public ObservationDTO findById(String id) {
        return observationRepository.findById(id)
                .map(e -> {
                    ObservationDTO observationDTO = new ObservationDTO();
                    observationDTO.setId(e.getId());
                    observationDTO.setDoctorName(e.getCreatedBy().getSurename());
                    observationDTO.setPatientName(e.getPatient().getSurename());
                    observationDTO.setDescription(e.getDescription());
                    observationDTO.setCreatedAt(e.getCreatedAt());
                    return observationDTO;
                })
                .orElseThrow(() -> new EntityNotFoundException("Encounter not found with id " + id));
    }
}

