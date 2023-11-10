package kth.alex.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "encounter")
public class Encounter {
    @Id
    private String id;

    @ManyToOne()
    @JoinColumn(name = "patient_social", nullable = false)
    private Patient patient;

    @ManyToOne()
    @JoinColumn(name="employeeId", nullable = false)
    private Doctor createdBy;

    @Column(name = "description")
    private String description;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    public Encounter() {
        super();
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "Encounter{" +
                "id=" + id +
                ", patient=" + patient +
                ", employee=" + createdBy +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
