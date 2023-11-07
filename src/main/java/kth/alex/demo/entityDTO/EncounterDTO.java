package kth.alex.demo.entityDTO;

import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Patient;

import java.time.LocalDateTime;

public class EncounterDTO {
    private Long id;
    private String patientName;
    private String doctorName;
    private String description;
    private LocalDateTime createdAt;

    public EncounterDTO(){
    }

    public EncounterDTO(Long id, String patientName, String doctorName, String description, LocalDateTime createdAt) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.description = description;
        this.createdAt = createdAt;
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
