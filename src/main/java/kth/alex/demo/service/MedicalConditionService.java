package kth.alex.demo.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kth.alex.demo.RequestBodyData.MedicalConditionCreate;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.MedicalCondition;
import kth.alex.demo.entity.Patient;
import kth.alex.demo.entityDTO.DoctorDTO;
import kth.alex.demo.entityDTO.MedicalConditionDTO;
import kth.alex.demo.repository.DoctorRepository;
import kth.alex.demo.repository.MedicalConditionRepository;
import kth.alex.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalConditionService {
    @Autowired
    private MedicalConditionRepository medicalConditionRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    public List<MedicalConditionDTO> getAll() {
        List<MedicalCondition> medicalConditions = medicalConditionRepository.findAll();

        List<MedicalConditionDTO> medicalConditionDTOS = new ArrayList<>();
        for (MedicalCondition m : medicalConditions) {
            MedicalConditionDTO medicalConditionDTO = new MedicalConditionDTO();
            medicalConditionDTO.setId(m.getId());
            medicalConditionDTO.setDiagnos(m.getDiagnos());
            medicalConditionDTO.setCreatedAt(m.getCreatedAt());

            medicalConditionDTOS.add(medicalConditionDTO);
        }
        return medicalConditionDTOS;
    }
    public MedicalConditionDTO findById(String id) {
        return medicalConditionRepository.findById(id)
                .map(m -> {
                    MedicalConditionDTO medicalConditionDTO = new MedicalConditionDTO();
                    medicalConditionDTO.setId(m.getId());

                    medicalConditionDTO.setDoctorEmployeeId(m.getDoctor().getEmployeeId());
                    medicalConditionDTO.setPatientSocialNr(m.getPatient().getSocialNr());
                    medicalConditionDTO.setDiagnos(m.getDiagnos());
                    medicalConditionDTO.setCreatedAt(m.getCreatedAt());
                    return medicalConditionDTO;
                })
                .orElseThrow(() -> new EntityNotFoundException("Encounter not found with id " + id));
    }

    @Transactional
    public void create(MedicalConditionCreate medicalConditionCreate){
        MedicalCondition medicalCondition = new MedicalCondition();

        Patient patient = patientRepository.findBySocialNr(medicalConditionCreate.getPatientSocialNr());
        Doctor doctor = doctorRepository.findByEmployeeId(medicalConditionCreate.getDoctorEmployeeId());

        medicalCondition.setPatient(patient);
        medicalCondition.setDoctor(doctor);
        medicalCondition.setDiagnos(medicalConditionCreate.getDiagnos());

        medicalConditionRepository.save(medicalCondition);
    }
}

