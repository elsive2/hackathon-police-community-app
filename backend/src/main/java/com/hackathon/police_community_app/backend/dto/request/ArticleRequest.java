package com.hackathon.police_community_app.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Boolean editable = true;
}
