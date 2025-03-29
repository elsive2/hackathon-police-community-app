package com.hackathon.police_community_app.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "article")
@Data
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "editable", nullable = false)
    private Boolean editable;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
}
