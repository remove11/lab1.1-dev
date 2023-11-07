package kth.alex.demo.entity;
import jakarta.persistence.*;
import kth.alex.demo.entityDTO.DoctorDTO;

import java.util.List;


@Entity
@Table(name = "doctor")
public class Doctor extends Employee{

    @OneToMany(mappedBy = "patient")
    private List<MedicalCondition> writenMedicalConditions;

    @Column(name = "degreeId")
    String degreeId;

    public Doctor() {
        super();
    }

    public Doctor(DoctorDTO doctorDTO){
        super(
                doctorDTO.surename(),
                doctorDTO.lastname(),
                doctorDTO.adress(),
                doctorDTO.socialNr(),
                doctorDTO.phoneNr(),
                doctorDTO.gender(),
                doctorDTO.employeeId()
        );
        this.degreeId=doctorDTO.degreeId();
    }

    public List<MedicalCondition> getWritenMedicalConditions() {
        return writenMedicalConditions;
    }

    public void setWritenMedicalConditions(List<MedicalCondition> writenMedicalConditions) {
        this.writenMedicalConditions = writenMedicalConditions;
    }

    public String getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(String degreeId) {
        this.degreeId = degreeId;
    }
}
