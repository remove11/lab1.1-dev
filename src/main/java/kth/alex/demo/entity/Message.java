package kth.alex.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sender_id", nullable = false)
    private Person sender;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="receiver_id", nullable = false)
    private Person receiver;
    @Column(name = "content")
    private String content;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public Message() {
        super();
        this.createdAt = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Person getSender() {
        return sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

