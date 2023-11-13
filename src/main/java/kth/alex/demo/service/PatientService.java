package kth.alex.demo.service;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import kth.alex.demo.entity.Patient;
import kth.alex.demo.entityDTO.PatientDTO;
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

    public List<PatientDTO> getAll() {
        List<Patient> patients = patientRepository.findAll();

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

    public PatientDTO getBySocial(String socialNr) {
        Patient p = patientRepository.findBySocialNr(socialNr);
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

    public PatientDTO save(UserCreationRequest patientDTO){
        Patient patient = new Patient(patientDTO);

        keycloakRepository.createUser(patientDTO).orElseThrow();
        UserRepresentation u = keycloakRepository.getUserByEmail(patientDTO.getEmail()).orElseThrow();

        patient.setKeycloakId(u.getId());

        Patient p = patientRepository.save(patient);
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

