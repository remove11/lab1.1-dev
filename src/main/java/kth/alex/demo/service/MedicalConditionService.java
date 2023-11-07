package kth.alex.demo.service;

import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.entity.Encounter;
import kth.alex.demo.entity.MedicalCondition;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.entityDTO.MedicalConditionDTO;
import kth.alex.demo.repository.EncounterRepository;
import kth.alex.demo.repository.MedicalConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalConditionService {
    @Autowired
    private MedicalConditionRepository medicalConditionRepository;

    public List<MedicalConditionDTO> getAll() {
        List<MedicalCondition> medicalConditions = medicalConditionRepository.findAll();

        List<MedicalConditionDTO> medicalConditionDTOS = new ArrayList<>();
        for (MedicalCondition m : medicalConditions) {
            MedicalConditionDTO medicalConditionDTO = new MedicalConditionDTO();
            medicalConditionDTO.setId(m.getId());
            medicalConditionDTO.setDiagnos(m.getDiagnos());
            medicalConditionDTO.setCreatedAt(m.getCreatedAt());

            medicalConditionDTOS.add(medicalConditionDTO);
        }
        return medicalConditionDTOS;
    }



    public MedicalConditionDTO findById(Long id) {
        return medicalConditionRepository.findById(id)
                .map(m -> {
                    MedicalConditionDTO medicalConditionDTO = new MedicalConditionDTO();
                    medicalConditionDTO.setId(m.getId());
                    medicalConditionDTO.setDoctorName(m.getDoctor().getSurename());
                    medicalConditionDTO.setPatientName(m.getPatient().getSurename());
                    medicalConditionDTO.setDiagnos(m.getDiagnos());
                    medicalConditionDTO.setCreatedAt(m.getCreatedAt());
                    return medicalConditionDTO;
                })
                .orElseThrow(() -> new EntityNotFoundException("Encounter not found with id " + id));
    }
}

