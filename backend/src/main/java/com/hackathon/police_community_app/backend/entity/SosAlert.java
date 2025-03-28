package com.hackathon.police_community_app.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sos_alert")
@Data
public class SosAlert extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
}
