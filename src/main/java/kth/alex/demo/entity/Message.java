package kth.alex.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "message")
public class Message {
    @Id
    private String id;
    @ManyToOne()
    @JoinColumn(name="sender_id", nullable = false)
    private Person sender;
    @ManyToOne()
    @JoinColumn(name="receiver_id", nullable = false)
    private Person receiver;
    @Column(name = "content")
    private String content;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    public Message() {
        super();
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }

    public void setId(String id) {
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

    public String getId() {
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
}

