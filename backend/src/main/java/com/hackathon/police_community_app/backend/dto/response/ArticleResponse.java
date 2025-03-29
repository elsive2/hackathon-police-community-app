package com.hackathon.police_community_app.backend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private Boolean isEditable;
    private LocalDateTime creationDate;
    private LocalDateTime changeDate;
    private UserResponse author;
}
