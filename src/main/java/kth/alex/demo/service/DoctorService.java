package kth.alex.demo.service;
import kth.alex.demo.Exeption.ClientErrorException;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entityDTO.DoctorDTO;
import kth.alex.demo.repository.DoctorRepository;
import kth.alex.demo.repository.KeycloakRepository;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private KeycloakRepository keycloakRepository;


    public List<DoctorDTO> getAll() throws ServerErrorException {
        List<Doctor> doctors;
        try{
            doctors = doctorRepository.findAll();
        }
        catch(Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

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

    public DoctorDTO getBySocial(String socialNr) throws ServerErrorException {
        Doctor d;
        try{
             d = doctorRepository.findBySocialNr(socialNr);
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }
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

    public DoctorDTO save(UserCreationRequest doctorCreation) throws ClientErrorException, ServerErrorException, NotFoundException {
        Doctor doctor = new Doctor(doctorCreation);

        UserRepresentation user = keycloakRepository.createUser(doctorCreation).orElse(null);

        if(user == null)
            throw new NotFoundException("User not found");

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

