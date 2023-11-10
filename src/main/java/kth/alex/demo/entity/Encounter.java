package kth.alex.demo.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "encounter")
@Getter
@Setter
@ToString
public class Encounter {
    @Id
    private String id;

    @ManyToOne()
    @JoinColumn(name = "patient_social", nullable = false)
    private Patient patient;

    @ManyToOne()
    @JoinColumn(name="employeeId", nullable = false)
    private Employee createdBy;

    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "encounter")
    private List<Observation> observations;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    public Encounter() {
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }

    public Encounter(String description) {
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }
}
