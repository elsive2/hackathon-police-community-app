package com.hackathon.police_community_app.backend.entity;

import com.hackathon.police_community_app.backend.enums.MessageStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "media_url", length = 255)
    private String mediaUrl;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private MessageStatus status;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "change_date")
    private LocalDateTime changeDate;

    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
        isDeleted = false;
        if (status == null) {
            status = MessageStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        changeDate = LocalDateTime.now();
    }
}