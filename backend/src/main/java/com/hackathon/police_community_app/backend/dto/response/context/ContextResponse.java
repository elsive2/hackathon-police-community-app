package com.hackathon.police_community_app.backend.dto.response.context;

import lombok.Data;

import java.util.List;

@Data
public class ContextResponse {
    private String name;
    private List<String> values;
}
