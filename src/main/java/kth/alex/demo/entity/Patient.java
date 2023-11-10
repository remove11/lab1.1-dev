package kth.alex.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kth.alex.demo.entityDTO.PatientDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patient")
@Getter
@Setter
@ToString
public class Patient extends Person{
    @OneToMany(mappedBy = "sender")
    private List<Message> sentMsg;
    @OneToMany(mappedBy = "receiver")
    private List<Message> receiverMsg;
    @OneToMany(mappedBy = "patient")
    private List<MedicalCondition> medicalConditions;

    @OneToMany(mappedBy = "patient")
    private List<Encounter> encounter;

    private LocalDateTime createdAt;

    public Patient() {
        super();
        this.createdAt = LocalDateTime.now();
    }

    public Patient(PatientDTO patientDTO){
        super(
                patientDTO.getSurename(),
                patientDTO.getLastname(),
                patientDTO.getAdress(),
                patientDTO.getSocialNr(),
                patientDTO.getPhoneNr(),
                patientDTO.getGender()
        );
        this.createdAt = patientDTO.getCreatedAt();
    }

    public Patient(String surename, String lastname, String adress, String socialNr, String phoneNr, Gender gender, LocalDateTime createdAt) {
        super(surename, lastname, adress, socialNr, phoneNr, gender);
        this.createdAt = createdAt;
    }
}
