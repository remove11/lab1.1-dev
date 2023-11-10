package kth.alex.demo.service;

import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Patient;
import kth.alex.demo.entityDTO.DoctorDTO;
import kth.alex.demo.entityDTO.PatientDTO;
import kth.alex.demo.repository.DoctorRepository;
import kth.alex.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

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

    public List<PatientDTO> getBySocial(String socialNr) {
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

    public PatientDTO save(PatientDTO patientDTO){
        Patient patient = new Patient(patientDTO);
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

