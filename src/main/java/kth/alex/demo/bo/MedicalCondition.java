package kth.alex.demo.bo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "medicalcondition")
public class MedicalCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="socialNr", referencedColumnName="socialNr")
    private Patient patient;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    public MedicalCondition() {
        super();
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
