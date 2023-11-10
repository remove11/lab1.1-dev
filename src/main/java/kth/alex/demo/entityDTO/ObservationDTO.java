package kth.alex.demo.entityDTO;

import java.time.LocalDateTime;

public class ObservationDTO {
    private String id;
    private String patientName;
    private String doctorName;
    private String description;
    private LocalDateTime createdAt;

    public ObservationDTO(){
    }

    public ObservationDTO(String id, String patientName, String doctorName, String description, LocalDateTime createdAt) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
