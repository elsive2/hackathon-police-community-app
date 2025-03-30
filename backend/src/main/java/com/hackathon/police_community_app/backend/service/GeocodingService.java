package com.hackathon.police_community_app.backend.service;

public interface GeocodingService {
    String getAddressByCoordinates(double latitude, double longitude);
}
