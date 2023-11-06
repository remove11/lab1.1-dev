package kth.alex.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medicalcondition")
public class MedicalCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_social", nullable = false)
    private Patient patient;

    @Column(name = "diagnos")
    private String diagnos;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    public MedicalCondition() {
        super();
        this.createdAt = LocalDateTime.now();
    }

    public String getDiagnos() {
        return diagnos;
    }

    public void setDiagnos(String diagnos) {
        this.diagnos = diagnos;
    }

    @Override
    public String toString() {
        return "MedicalCondition{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", diagnos='" + diagnos + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
