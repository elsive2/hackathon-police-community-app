package com.hackathon.police_community_app.backend.service.sms_provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Primary
@Component
public class UnimtxSmsProvider implements SmsProvider {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${unimtx.api.key}")
    private String accessKeyId;

    @Value("${unimtx.api.url}")
    private String apiUrl;

    public UnimtxSmsProvider(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String sendSms(String phoneNumber, String code) {
        String url = apiUrl + "&accessKeyId=" + accessKeyId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("User-Agent", "Hackathon/1.0 (maximyakovlev228@gmail.com)");

        SmsRequest smsRequest = new SmsRequest(normalizePhoneNumber(phoneNumber), code);
        HttpEntity<SmsRequest> requestEntity = new HttpEntity<>(smsRequest, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            SmsResponse result = objectMapper.readValue(response.getBody(), SmsResponse.class);

            if (response.getStatusCode().is2xxSuccessful() && result.getCode() == null) {
                return "SMS успешно отправлено на " + phoneNumber;
            } else {
                return "Ошибка отправки SMS: " + result.getMessage();
            }

        } catch (Exception e) {
            return "Ошибка отправки SMS: " + e.getMessage();
        }
    }

    @Data
    @AllArgsConstructor
    private static class SmsRequest {
        private String to;
        private String code;
    }

    @Data
    private static class SmsResponse {
        @JsonProperty("code")
        private String code;

        @JsonProperty("message")
        private String message;
    }

    private String normalizePhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.startsWith("8")) {
            return "+7" + phoneNumber.substring(1);
        }
        return phoneNumber; // Оставляем без изменений, если уже в формате +7 или другой
    }
}