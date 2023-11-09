package kth.alex.demo.entity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee extends Person {
    @Column(name = "employeeId", unique = true)
    private String employeeId;

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMessages;

    @OneToMany(mappedBy = "createdBy")
    private List<Encounter> encounter;

    public Employee(String surename, String lastname, String adress, String socialNr, String phoneNr, Gender gender, String employeeId) {
        super(surename, lastname, adress, socialNr, phoneNr, gender);
        this.employeeId = employeeId;
    }

    public Employee() {
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", sentMessages=" + sentMessages +
                ", receivedMessages=" + receivedMessages +
                '}';
    }
}
