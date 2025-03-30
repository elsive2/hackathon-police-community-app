package com.hackathon.police_community_app.backend.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class QueryRequest {
    private String content;
    private String title;
    private List<String> photos;
    private Double latitude;
    private Double longitude;
    private String category;
}
