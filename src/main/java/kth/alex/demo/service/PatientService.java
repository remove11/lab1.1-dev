package kth.alex.demo.service;

import kth.alex.demo.Exeption.ClientErrorException;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Patient;
import kth.alex.demo.entityDTO.DoctorDTO;
import kth.alex.demo.entityDTO.PatientDTO;
import kth.alex.demo.repository.DoctorRepository;
import kth.alex.demo.repository.KeycloakRepository;
import kth.alex.demo.repository.PatientRepository;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private KeycloakRepository keycloakRepository;

    public List<PatientDTO> getAll() throws ServerErrorException {
        List<Patient> patients;

        try{
            patients = patientRepository.findAll();
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

        List<PatientDTO> patientDTOs = new ArrayList<>();
        for (Patient p : patients) {
            PatientDTO patientDTO = new PatientDTO(
                    p.getSocialNr(),
                    p.getSurename(),
                    p.getLastname(),
                    p.getAdress(),
                    p.getPhoneNr(),
                    p.getGender(),
                    p.getKeycloakId(),
                    p.getCreatedAt()
            );
            patientDTOs.add(patientDTO);
        }
        return patientDTOs;
    }

    public PatientDTO getBySocial(String socialNr) throws ServerErrorException, NotFoundException {
        Patient p;

        try {
            p = patientRepository.findBySocialNr(socialNr);
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

        if(p == null)
            throw new NotFoundException("Patient not found");

        return new PatientDTO(
                p.getSocialNr(),
                p.getSurename(),
                p.getLastname(),
                p.getAdress(),
                p.getPhoneNr(),
                p.getGender(),
                p.getKeycloakId(),
                p.getCreatedAt()
        );
    }

    public PatientDTO save(UserCreationRequest patientDTO) throws ClientErrorException, ServerErrorException {
        Patient patient = new Patient(patientDTO);

        keycloakRepository.createUser(patientDTO).orElseThrow();
        UserRepresentation u = keycloakRepository.getUserByEmail(patientDTO.getEmail()).orElseThrow();

        patient.setKeycloakId(u.getId());

        Patient p;

        try{
            p = patientRepository.save(patient);
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

        return new PatientDTO(
                p.getSocialNr(),
                p.getSurename(),
                p.getLastname(),
                p.getAdress(),
                p.getPhoneNr(),
                p.getGender(),
                p.getKeycloakId(),
                p.getCreatedAt()
        );
    }
}

