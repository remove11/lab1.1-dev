package kth.alex.demo.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "medicalcondition")
@Getter
@Setter
@ToString
public class MedicalCondition {
    @Id
    private String id;

    @ManyToOne()
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne()
    @JoinColumn(name = "patient_social", nullable = false)
    private Patient patient;

    @Column(name = "diagnos")
    private String diagnos;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    public MedicalCondition() {
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }

    public MedicalCondition(String diagnos) {
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.diagnos = diagnos;
    }
}
