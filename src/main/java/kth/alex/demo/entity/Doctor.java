package kth.alex.demo.entity;
import jakarta.persistence.*;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
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

    @OneToMany(mappedBy = "createdBy")
    private List<Observation> writenObservations;

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
                doctorDTO.getSurename(),
                doctorDTO.getLastname(),
                doctorDTO.getAdress(),
                doctorDTO.getSocialNr(),
                doctorDTO.getPhoneNr(),
                doctorDTO.getGender(),
                doctorDTO.getEmployeeId()
        );
        this.degreeId=doctorDTO.getDegreeId();
    }

    public Doctor(UserCreationRequest doctorCreate){
        super(
                doctorCreate.getSurename(),
                doctorCreate.getLastname(),
                doctorCreate.getAdress(),
                doctorCreate.getSocialNr(),
                doctorCreate.getPhoneNr(),
                doctorCreate.getGender(),
                doctorCreate.getEmployeeId()
        );
        this.degreeId=doctorCreate.getDegreeId();
    }
}
