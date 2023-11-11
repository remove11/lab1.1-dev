package kth.alex.demo.service;

import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
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

    public List<ObservationDTO> getAll() throws ServerErrorException {
        List<Observation> observations;

        try {
            observations = observationRepository.findAll();
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

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



    public ObservationDTO findById(String id) throws NotFoundException {
        Observation e = observationRepository
                .findById(id)
                .orElseThrow(()->new NotFoundException("Observation not found"));

        ObservationDTO observationDTO = new ObservationDTO();
        observationDTO.setId(e.getId());
        observationDTO.setDoctorName(e.getCreatedBy().getSurename());
        observationDTO.setPatientName(e.getPatient().getSurename());
        observationDTO.setDescription(e.getDescription());
        observationDTO.setCreatedAt(e.getCreatedAt());

        return observationDTO;
    }
}

