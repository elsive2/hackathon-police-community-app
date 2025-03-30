package com.hackathon.police_community_app.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleRequest {
    @NotBlank(message = "Заголовок обязательное поле")
    private String title;

    @NotBlank(message = "Текст обязательное поле")
    private String content;

    private Boolean editable = true;
}
