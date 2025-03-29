package com.hackathon.police_community_app.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priority {
    CRITICAL(4),
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private final int value;
}
