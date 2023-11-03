package kth.alex.demo.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Employee extends Person {
    @Column(name = "employeeId")
    private int employeeId;



    /*
    @OneToMany(mappedBy = "patient")
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "employee")
    private List<Message> receivedMessages;

    public List<Message> getMessages() {
        List<Message> allMessages = new ArrayList<>();
        allMessages.addAll(sentMessages);
        allMessages.addAll(receivedMessages);
        return allMessages;
    }


     */
    public int getEmployeeId() {
        return employeeId;
    }
}
