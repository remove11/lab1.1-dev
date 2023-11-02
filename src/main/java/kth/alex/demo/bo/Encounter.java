package kth.alex.demo.bo;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "encounter")
public class Encounter {
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

    public Encounter() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
