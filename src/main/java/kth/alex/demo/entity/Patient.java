package kth.alex.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kth.alex.demo.entityDTO.PatientDTO;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patient")
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
                patientDTO.surename(),
                patientDTO.lastname(),
                patientDTO.adress(),
                patientDTO.socialNr(),
                patientDTO.phoneNr(),
                patientDTO.gender()
        );
        this.createdAt = patientDTO.createdAt();
    }

    public Patient(List<Message> sentMsg, List<Message> receiverMsg, List<MedicalCondition> medicalConditions, List<Encounter> encounter, LocalDateTime createdAt) {
        this.sentMsg = sentMsg;
        this.receiverMsg = receiverMsg;
        this.medicalConditions = medicalConditions;
        this.encounter = encounter;
        this.createdAt = createdAt;
    }

    public Patient(String surename, String lastname, String adress, String socialNr, String phoneNr, Gender gender, List<Message> sentMsg, List<Message> receiverMsg, List<MedicalCondition> medicalConditions, List<Encounter> encounter, LocalDateTime createdAt) {
        super(surename, lastname, adress, socialNr, phoneNr, gender);
        this.sentMsg = sentMsg;
        this.receiverMsg = receiverMsg;
        this.medicalConditions = medicalConditions;
        this.encounter = encounter;
        this.createdAt = createdAt;
    }

    @Override
    public List<Message> getSentMsg() {
        return sentMsg;
    }

    @Override
    public void setSentMsg(List<Message> sentMsg) {
        this.sentMsg = sentMsg;
    }

    public List<Message> getReceiverMsg() {
        return receiverMsg;
    }

    public void setReceiverMsg(List<Message> receiverMsg) {
        this.receiverMsg = receiverMsg;
    }

    public List<MedicalCondition> getMedicalConditions() {
        return medicalConditions;
    }

    public void setMedicalConditions(List<MedicalCondition> medicalConditions) {
        this.medicalConditions = medicalConditions;
    }

    public List<Encounter> getEncounter() {
        return encounter;
    }

    public void setEncounter(List<Encounter> encounter) {
        this.encounter = encounter;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
