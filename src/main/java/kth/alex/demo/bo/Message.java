package kth.alex.demo.bo;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="socialNr", referencedColumnName="socialNr")
    private Patient patient;

    @Column(name = "content")
    private String content;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name="employeeId", referencedColumnName="employeeId")
    private Person employee;

    @ManyToOne
    @JoinColumn(name="sender_id", referencedColumnName="employeeId")
    private Person sender;

    @ManyToOne
    @JoinColumn(name="recipient_id", referencedColumnName="employeeId")
    private Person recipient;

    public Message() {
        super();
        this.createdAt = LocalDateTime.now();
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Person getEmployee() {
        return employee;
    }

    public void setEmployee(Person employee) {
        this.employee = employee;
    }
}
