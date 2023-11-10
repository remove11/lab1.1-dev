package kth.alex.demo.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kth.alex.demo.RequestBodyData.EncounterCreate;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Encounter;
import kth.alex.demo.entity.Patient;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.repository.DoctorRepository;
import kth.alex.demo.repository.EncounterRepository;
import kth.alex.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EncounterService {
    @Autowired
    private EncounterRepository encounterRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

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


    public EncounterDTO findById(String id) {
        return encounterRepository.findById(id)
            .map(e -> {
                EncounterDTO encounterDTO = new EncounterDTO();
                encounterDTO.setId(e.getId());
                encounterDTO.setDoctorName(e.getCreatedBy().getSurename());
                encounterDTO.setPatientName(e.getPatient().getSurename());
                encounterDTO.setDescription(e.getDescription());
                encounterDTO.setCreatedAt(e.getCreatedAt());
                return encounterDTO;
            })
            .orElseThrow(() -> new EntityNotFoundException("Encounter not found with id " + id));
    }

    @Transactional
    public void create(EncounterCreate encounterCreate) {
        Encounter encounter = new Encounter();

        Patient patient = patientRepository.findBySocialNr(encounterCreate.getPatientSocialNr());
        Doctor doctor = doctorRepository.findByEmployeeId(encounterCreate.getDoctorEmployeeId());

        encounter.setPatient(patient);
        encounter.setCreatedBy(doctor);
        encounter.setDescription(encounterCreate.getDescription());

        encounterRepository.save(encounter);
    }
}

