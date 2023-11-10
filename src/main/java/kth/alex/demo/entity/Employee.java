package kth.alex.demo.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
public abstract class Employee extends Person {
    @Column(name = "employeeId", unique = true)
    private String employeeId;

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMessages;

    @OneToMany(mappedBy = "createdBy")
    private List<Encounter> createdEncounter;

    public Employee(String surename, String lastname, String adress, String socialNr, String phoneNr, Gender gender, String employeeId) {
        super(surename, lastname, adress, socialNr, phoneNr, gender);
        this.employeeId = employeeId;
    }

    public Employee(String surename, String lastname, String adress, String socialNr, String phoneNr, Gender gender) {
        super(surename, lastname, adress, socialNr, phoneNr, gender);
        this.employeeId = UUID.randomUUID().toString();
    }

    public Employee() {
        this.employeeId = UUID.randomUUID().toString();
    }
}
