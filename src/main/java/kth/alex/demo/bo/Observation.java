package kth.alex.demo.bo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "observation")
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="socialNr", referencedColumnName="socialNr")
    private Patient patient;

    @Column(name = "description")
    private String description;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    public Observation() {
    }

    public Patient getPatient() {  // Change Person to Patient
        return patient;
    }

    public void setPatient(Patient patient) {  // Change Person to Patient
        this.patient = patient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "description='" + description + '\'' +
                '}';
    }

}