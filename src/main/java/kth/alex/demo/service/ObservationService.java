package kth.alex.demo.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.EncounterCreate;
import kth.alex.demo.RequestBodyData.ObservationCreate;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Encounter;
import kth.alex.demo.entity.Observation;
import kth.alex.demo.entity.Patient;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.entityDTO.ObservationDTO;
import kth.alex.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObservationService {
    @Autowired
    private ObservationRepository observationRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    IdentityRepository identityRepository;
    @Autowired
    EncounterRepository encounterRepository;

    @Transactional
    public void create(ObservationCreate observationCreate) throws ServerErrorException, NotFoundException {
        Observation observation = new Observation();

        String keycloakId = identityRepository
                .getUserId()
                .orElseThrow(() -> new NotFoundException("User Not found"));

        System.out.println("keycloakId: "+keycloakId);

        Patient patient;
        Doctor doctor;
        Encounter encounter;

        try {
            patient = patientRepository.findBySocialNr(observationCreate.getPatientSocialNr());
            doctor = doctorRepository.findByKeycloakId(keycloakId);
            encounter = encounterRepository.findById(observationCreate.getEncounterId()).orElse(null);
        } catch (Exception ex) {
            throw new ServerErrorException(ex.getMessage());
        }

        if (patient == null)
            throw new NotFoundException("Patient not found");
        if (doctor == null)
            throw new NotFoundException("Doctor not found");
        if (encounter == null)
            throw new NotFoundException("Encounter not found");

        observation.setPatient(patient);
        observation.setCreatedBy(doctor);
        observation.setEncounter(encounter);
        observation.setDescription(observationCreate.getDescription());

        try {
            observationRepository.save(observation);
        } catch (Exception ex) {
            throw new ServerErrorException(ex.getMessage());
        }
    }


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
            observationDTO.setPatientName(o.getPatient().getLastname() + " " + o.getPatient().getSurename());
            observationDTO.setDoctorName(o.getCreatedBy().getLastname() + " " + o.getPatient().getSurename());
            observationDTOs.add(observationDTO);
        }
        return observationDTOs;
    }


    public List<ObservationDTO> getByEncounterId(String socialNr) throws ServerErrorException {
        List<Observation> observations;

        try {
            observations = observationRepository.findByEncounterId(socialNr);
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

        List<ObservationDTO> observationDTOs = new ArrayList<>();
        for (Observation o : observations) {
            ObservationDTO observationDTO = new ObservationDTO();
            observationDTO.setId(o.getId());
            observationDTO.setDescription(o.getDescription());
            observationDTO.setCreatedAt(o.getCreatedAt());
            observationDTO.setPatientName(o.getPatient().getLastname() + " " + o.getPatient().getSurename());
            observationDTO.setDoctorName(o.getCreatedBy().getLastname() + " " + o.getPatient().getSurename());
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

