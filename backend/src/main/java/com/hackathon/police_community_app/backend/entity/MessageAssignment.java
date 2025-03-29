package com.hackathon.police_community_app.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "message_assignment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private ChatMessage message;

    @ManyToOne
    @JoinColumn(name = "assignee_id", nullable = false)
    private User assignee;

    @Column(nullable = false)
    private LocalDateTime assignDate;

    @Column(nullable = false)
    private boolean isActive = true;
}