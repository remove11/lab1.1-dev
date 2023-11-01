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

    public Message() {
        super();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return createdAt;
    }

    public void setTimestamp(LocalDateTime createdAt) {
        this.createdAt = LocalDateTime.now();
    }
}
