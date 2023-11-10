package kth.alex.demo.service;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entityDTO.DoctorDTO;
import kth.alex.demo.repository.DoctorRepository;
import kth.alex.demo.repository.KeycloakRepository;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private KeycloakRepository keycloakRepository;


    public List<DoctorDTO> getAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOs = new ArrayList<>();
        for (Doctor d : doctors) {
            DoctorDTO doctorDTO = new DoctorDTO(
                    d.getSocialNr(),
                    d.getSurename(),
                    d.getLastname(),
                    d.getAdress(),
                    d.getPhoneNr(),
                    d.getGender(),
                    d.getDegreeId(),
                    d.getEmployeeId()
            );
            doctorDTOs.add(doctorDTO);
        }
        return doctorDTOs;
    }

    public DoctorDTO getBySocial(String socialNr) {
        Doctor d = doctorRepository.findBySocialNr(socialNr);
        return new DoctorDTO(
                d.getSocialNr(),
                d.getSurename(),
                d.getLastname(),
                d.getAdress(),
                d.getPhoneNr(),
                d.getGender(),
                d.getDegreeId(),
                d.getEmployeeId()
        );
    }

    public DoctorDTO save(UserCreationRequest doctorCreation){
        Doctor doctor = new Doctor(doctorCreation);

        UserRepresentation user = keycloakRepository.createUser(doctorCreation).orElseThrow();

        doctor.setKeycloakId(user.getId());

        Doctor d = doctorRepository.save(doctor);

        return new DoctorDTO(
                d.getSocialNr(),
                d.getSurename(),
                d.getLastname(),
                d.getAdress(),
                d.getPhoneNr(),
                d.getGender(),
                d.getDegreeId(),
                d.getEmployeeId()
        );
    }
}

