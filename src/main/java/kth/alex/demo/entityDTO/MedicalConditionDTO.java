package kth.alex.demo.entityDTO;

import java.time.LocalDateTime;

public class MedicalConditionDTO {
    private Long id;
    private String patientName;
    private String doctorName;
    private String diagnos;
    private LocalDateTime createdAt;

    public MedicalConditionDTO(){
    }

    public MedicalConditionDTO(Long id, String patientName, String doctorName, String diagnos, LocalDateTime createdAt) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.diagnos = diagnos;
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

    public String getDiagnos() {
        return diagnos;
    }

    public void setDiagnos(String diagnos) {
        this.diagnos = diagnos;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
