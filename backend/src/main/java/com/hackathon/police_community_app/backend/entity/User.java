package com.hackathon.police_community_app.backend.entity;

import com.hackathon.police_community_app.backend.enums.Role;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "user")
@Data
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "password_hash", length = 100)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private Role role;
}