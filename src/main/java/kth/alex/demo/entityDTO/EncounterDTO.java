package kth.alex.demo.entityDTO;

import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Patient;

import java.time.LocalDateTime;

public class EncounterDTO {
    private Long id;
    private Patient patient;
    private Doctor createdBy;
    private String description;
    private LocalDateTime createdAt;

    public EncounterDTO(){
    }
    public EncounterDTO(Long id, Patient patient, Doctor createdBy, String description, LocalDateTime createdAt) {
        this.id = id;
        this.patient = patient;
        this.createdBy = createdBy;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Doctor createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
