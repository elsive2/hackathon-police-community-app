package com.hackathon.police_community_app.backend.entity;

import com.hackathon.police_community_app.backend.enums.QueryCategory;
import com.hackathon.police_community_app.backend.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class Query extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "responsible_id", nullable = true)
    private User responsible;

    @Column(nullable = false, length = 500)
    private String content;

    @OneToMany(mappedBy = "query", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QueryPhoto> photos = new ArrayList<>();

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private QueryCategory category;
}