package com.hackathon.police_community_app.backend.dto.response.context;

import com.hackathon.police_community_app.backend.dto.response.KeyValueResponse;
import lombok.Data;

import java.util.List;

@Data
public class ContextKeyValueResponse {
    private String name;
    private List<KeyValueResponse> values;
}
