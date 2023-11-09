package kth.alex.demo.service;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entityDTO.DoctorDTO;
import kth.alex.demo.repository.DoctorRepository;
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

    public DoctorDTO getBySocial(String socialNr) {
        System.out.println("Här är i service social=" + socialNr);
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

