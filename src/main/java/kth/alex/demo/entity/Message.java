package kth.alex.demo.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "message")
@Getter
@Setter
@ToString
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
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    public Message(String content) {
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }
}

