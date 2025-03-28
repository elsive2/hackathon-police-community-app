package com.hackathon.police_community_app.backend.entity;

import com.hackathon.police_community_app.backend.enums.MessageStatus;
import com.hackathon.police_community_app.backend.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false, length = 500)
    private String content;

    @Column
    private String mediaUrl;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @Column(nullable = false, length = 50)
    private String category;
}