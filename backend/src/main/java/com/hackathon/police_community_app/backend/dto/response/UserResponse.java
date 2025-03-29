package com.hackathon.police_community_app.backend.dto.response;

import com.hackathon.police_community_app.backend.enums.Role;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String phoneNumber;
    private Role role;
}
