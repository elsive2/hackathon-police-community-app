package com.hackathon.police_community_app.backend.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class QueryRequest {
    @NotBlank(message = "Содержание не может быть пустым")
    @Size(max = 1000, message = "Содержание должно быть не более 1000 символов")
    private String content;

    @NotBlank(message = "Заголовок не может быть пустым")
    @Size(max = 100, message = "Заголовок должен быть не более 100 символов")
    private String title;

    @Size(max = 10, message = "Максимально допустимо 10 фотографий")
    private List<String> photos;

    @DecimalMin(value = "-90.0", message = "Широта должна быть в диапазоне от -90 до 90")
    @DecimalMax(value = "90.0", message = "Широта должна быть в диапазоне от -90 до 90")
    private Double latitude;

    @DecimalMin(value = "-180.0", message = "Долгота должна быть в диапазоне от -180 до 180")
    @DecimalMax(value = "180.0", message = "Долгота должна быть в диапазоне от -180 до 180")
    private Double longitude;

    private String category;
}
