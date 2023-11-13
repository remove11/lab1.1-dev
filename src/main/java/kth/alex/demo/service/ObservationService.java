package kth.alex.demo.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kth.alex.demo.RequestBodyData.EncounterCreate;
import kth.alex.demo.RequestBodyData.ObservationCreate;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Encounter;
import kth.alex.demo.entity.Observation;
import kth.alex.demo.entity.Patient;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.entityDTO.ObservationDTO;
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
    EncounterRepository encounterRepository;

    @Autowired
    DoctorRepository doctorRepository;

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

    @Transactional
    public void create(ObservationCreate observationCreate) {
        Observation observation = new Observation();

        Encounter encounter = encounterRepository.getReferenceById(observationCreate.getEncounterId());
        Patient patient = patientRepository.findBySocialNr(observationCreate.getPatientSocialNr());
        Doctor doctor = doctorRepository.findByEmployeeId(observationCreate.getDoctorEmployeeId());

        observation.setEncounter(encounter);
        observation.setPatient(patient);
        observation.setCreatedBy(doctor);
        observation.setDescription(observationCreate.getDescription());

        try{
            observationRepository.save(observation);

        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}

