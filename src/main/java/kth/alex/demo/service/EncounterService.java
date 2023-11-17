package kth.alex.demo.service;

import jakarta.transaction.Transactional;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.EncounterCreate;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Encounter;
import kth.alex.demo.entity.Patient;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.repository.*;
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
    @Autowired
    IdentityRepository identityRepository;
    @Autowired
    ObservationRepository observationRepository;

    public List<EncounterDTO> getAll() throws ServerErrorException {
        List<Encounter> encounters;
        try{
             encounters = encounterRepository.findAll();
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

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

    public List<EncounterDTO> findByPersonNr(String personNr) throws ServerErrorException {
        List<Encounter> encounters;
        try{
            encounters = encounterRepository.findByPersonNr(personNr);
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

        List<EncounterDTO> encounterDTOs = new ArrayList<>();
        for (Encounter e : encounters) {
            EncounterDTO encounterDTO = new EncounterDTO();
            encounterDTO.setId(e.getId());
            encounterDTO.setDescription(e.getDescription());
            encounterDTO.setCreatedAt(e.getCreatedAt());
            encounterDTO.setPatientName(e.getPatient().getLastname() + " " + e.getPatient().getSurename());
            encounterDTO.setDoctorName(e.getCreatedBy().getLastname() + " " + e.getCreatedBy().getSurename());
            encounterDTO.setDoctorEmployeeId(e.getCreatedBy().getEmployeeId());
            encounterDTO.setPatientSocialNr(e.getPatient().getSocialNr());

            encounterDTOs.add(encounterDTO);
        }
        return encounterDTOs;
    }

    public EncounterDTO findById(String id) throws NotFoundException {
        Encounter e = encounterRepository
                .findById(id)
                .orElseThrow(()-> new NotFoundException("Encounter not found"));

        EncounterDTO encounterDTO = new EncounterDTO();
        encounterDTO.setId(e.getId());
        encounterDTO.setDoctorName(e.getCreatedBy().getSurename());
        encounterDTO.setPatientName(e.getPatient().getSurename());
        encounterDTO.setDescription(e.getDescription());
        encounterDTO.setCreatedAt(e.getCreatedAt());
        encounterDTO.setPatientSocialNr(e.getPatient().getSocialNr());
        encounterDTO.setDoctorEmployeeId(e.getCreatedBy().getEmployeeId());

        return encounterDTO;
    }

    @Transactional
    public void create(EncounterCreate encounterCreate) throws ServerErrorException, NotFoundException {
        Encounter encounter = new Encounter();

        Patient patient;
        Doctor doctor;

        try {
            patient = patientRepository.findBySocialNr(encounterCreate.getPatientSocialNr());
            doctor = doctorRepository.findByEmployeeId(encounterCreate.getDoctorEmployeeId());
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

        if(patient == null)
            throw new NotFoundException("Patient not found");
        if(doctor == null)
            throw new NotFoundException("Doctor not found");

        encounter.setPatient(patient);
        encounter.setCreatedBy(doctor);
        encounter.setDescription(encounterCreate.getDescription());

        try{
            encounterRepository.save(encounter);
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }
    }
}