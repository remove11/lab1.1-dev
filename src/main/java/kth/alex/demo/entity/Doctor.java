package kth.alex.demo.entity;
import jakarta.persistence.*;
import kth.alex.demo.entityDTO.DoctorDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Table(name = "doctor")
@Getter
@Setter
@ToString
public class Doctor extends Employee{

    @OneToMany(mappedBy = "patient")
    private List<MedicalCondition> writenMedicalConditions;

    @Column(name = "degreeId")
    String degreeId;

    public Doctor() {
        super();
    }

    public Doctor(String surename, String lastname, String adress, String socialNr, String phoneNr, Gender gender, String employeeId, String degreeId) {
        super(surename, lastname, adress, socialNr, phoneNr, gender, employeeId);
        this.degreeId = degreeId;
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
}
