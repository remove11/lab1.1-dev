package kth.alex.demo.service;

import jakarta.transaction.Transactional;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.ObservationCreate;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Encounter;
import kth.alex.demo.entity.Observation;
import kth.alex.demo.entity.Patient;
import kth.alex.demo.entityDTO.ObservationDTO;
import kth.alex.demo.repository.*;
import kth.alex.demo.repository.DoctorRepository;
import kth.alex.demo.repository.EncounterRepository;
import kth.alex.demo.repository.ObservationRepository;
import kth.alex.demo.repository.PatientRepository;
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

    public List<ObservationDTO> findListById(String id) throws NotFoundException {
        List<ObservationDTO> observationDTOs = new ArrayList<>();
        List<Observation> observations = observationRepository.findListById(id);

        for (Observation o : observations) {
            observationDTOs.add(new ObservationDTO(
                    o.getId(),
                    o.getPatient().getSurename(),
                    o.getPatient().getSocialNr(),
                    o.getCreatedBy().getSurename(),
                    o.getCreatedBy().getEmployeeId(),
                    o.getDescription(),
                    o.getEncounter().getId(),
                    o.getCreatedAt()
            ));
        }
            return observationDTOs;
    }

    @Transactional
    public void create(ObservationCreate observationCreate) throws ServerErrorException {
        Observation observation = new Observation();

        Encounter encounter;
        Patient patient;
        Doctor doctor;

        String doctorId = identityRepository.getUserId().orElseThrow(()->new ServerErrorException("Can not get doctor id"));

        try{
            encounter = encounterRepository.getReferenceById(observationCreate.getEncounterId());
            patient = patientRepository.findBySocialNr(observationCreate.getPatientSocialNr());
            doctor = doctorRepository.findByKeycloakId(doctorId);
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

        observation.setEncounter(encounter);
        observation.setPatient(patient);
        observation.setCreatedBy(doctor);
        observation.setDescription(observationCreate.getDescription());

        try{
            observationRepository.save(observation);

        }catch (Exception ex)
        {
            throw new ServerErrorException(ex.getMessage());
        }
    }
}

