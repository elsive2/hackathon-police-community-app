package com.hackathon.police_community_app.backend.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class OpenStreetGeoService implements GeocodingService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Cacheable("addresses")
    public String getAddressByCoordinates(double latitude, double longitude) {
        String url = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + latitude + "&lon=" + longitude;

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Hackahton/1.0 (maximyakovlev228@gmail.com)");

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );

            SimpleResponse result = objectMapper.readValue(response.getBody(), SimpleResponse.class);

            return result.getDisplayName() != null
                    ? result.getDisplayName().substring(0, Math.min(result.getDisplayName().length(), 255))
                    : "Адрес не найден";

        } catch (Exception e) {
            return "Ошибка получения адреса: " + e.getMessage();
        }
    }

    @Data
    private static class SimpleResponse {
        @JsonProperty("display_name")
        private String displayName;
    }
}
