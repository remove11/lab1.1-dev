package kth.alex.demo.entityDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kth.alex.demo.entity.Person;

import java.time.LocalDateTime;

public class MessageDTO {
    private Long id;
    private String senderName;
    private String receiverName;
    private String content;
    private LocalDateTime createdAt;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String senderName, String receiverName, String content, LocalDateTime createdAt) {
        this.id = id;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
