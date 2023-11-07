package kth.alex.demo.entityDTO;

import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.MedicalCondition;
import kth.alex.demo.entity.Patient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DoctorDTO {
    private List<MedicalCondition> writenMedicalCondition;
    private String degreeId;

    public DoctorDTO(){
    }

    public DoctorDTO(List<MedicalCondition> writenMedicalCondition, String degreeId) {
        this.writenMedicalCondition = new ArrayList<>();
        this.degreeId = degreeId;
    }

    public List<MedicalCondition> getWritenMedicalCondition() {
        return writenMedicalCondition;
    }

    public void setWritenMedicalCondition(List<MedicalCondition> writenMedicalCondition) {
        this.writenMedicalCondition = writenMedicalCondition;
    }

    public String getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(String degreeId) {
        this.degreeId = degreeId;
    }

}
