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

    @ManyToOne
    @JoinColumn(name="employeeId", referencedColumnName="employeeId")
    private Person employee;
    @Column(name = "description")
    private String description;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    public Observation() {
        super();
        this.createdAt = LocalDateTime.now();
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Person getEmployee() {
        return employee;
    }

    public void setEmployee(Person employee) {
        this.employee = employee;
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