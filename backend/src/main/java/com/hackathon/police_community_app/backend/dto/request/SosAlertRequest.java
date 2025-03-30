package com.hackathon.police_community_app.backend.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SosAlertRequest {
    @DecimalMin(value = "-90.0", message = "Широта должна быть в диапазоне от -90 до 90")
    @DecimalMax(value = "90.0", message = "Широта должна быть в диапазоне от -90 до 90")
    private Double latitude;

    @DecimalMin(value = "-180.0", message = "Долгота должна быть в диапазоне от -180 до 180")
    @DecimalMax(value = "180.0", message = "Долгота должна быть в диапазоне от -180 до 180")
    private Double longitude;

    @NotBlank(message = "Содержание не может быть пустым")
    @Size(max = 255, message = "Содержание должно быть не более 255 символов")
    private String comment;
}
