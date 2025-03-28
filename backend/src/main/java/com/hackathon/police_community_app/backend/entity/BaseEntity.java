package com.hackathon.police_community_app.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class BaseEntity {
    @Column(name = "is_deleted", nullable = false)
    protected Boolean isDeleted = false;

    @Column(name = "create_date", nullable = false)
    protected LocalDateTime createDate;

    @Column(name = "change_date")
    protected LocalDateTime changeDate;

    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
        isDeleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        changeDate = LocalDateTime.now();
    }
}
