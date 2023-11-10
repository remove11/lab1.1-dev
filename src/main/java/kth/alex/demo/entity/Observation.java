package kth.alex.demo.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "observation")
@Getter
@Setter
@ToString
public class Observation {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "patient_social", nullable = false)
    private Patient patient;

    @ManyToOne()
    @JoinColumn(name="employeeId", nullable = false)
    private Doctor createdBy;

    @ManyToOne()
    @JoinColumn(name = "observationId", nullable = false)
    private Encounter encounter;

    @Column(name = "description")
    private String description;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    public Observation(String description) {
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }

    public Observation() {
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }
}

