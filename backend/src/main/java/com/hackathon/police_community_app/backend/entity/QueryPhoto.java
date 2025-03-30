package com.hackathon.police_community_app.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "query_photo")
@Data
public class QueryPhoto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "query_id", nullable = false)
    private Query query;

    @Column(nullable = false)
    private String photoUrl;

    public QueryPhoto(String photoUrl, Query query) {
        this.photoUrl = photoUrl;
    }

    public QueryPhoto() {

    }
}