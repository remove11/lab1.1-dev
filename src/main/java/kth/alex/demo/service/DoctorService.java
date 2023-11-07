package kth.alex.demo.service;

import jakarta.persistence.EntityNotFoundException;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Encounter;
import kth.alex.demo.entityDTO.DoctorDTO;
import kth.alex.demo.entityDTO.EncounterDTO;
import kth.alex.demo.repository.DoctorRepository;
import kth.alex.demo.repository.EncounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

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

    public List<DoctorDTO> getBySocial(String socialNr) {
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

    public DoctorDTO save(DoctorDTO doctorDTO){
        Doctor doctor = new Doctor(doctorDTO);
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

