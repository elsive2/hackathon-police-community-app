package com.hackathon.police_community_app.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class KeyValueResponse {
    private String key;
    private String value;
}
