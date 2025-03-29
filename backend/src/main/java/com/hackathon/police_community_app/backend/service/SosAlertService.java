package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.request.SosAlertRequest;
import com.hackathon.police_community_app.backend.dto.response.PagedResponse;
import com.hackathon.police_community_app.backend.dto.response.SosAlertResponse;

public interface SosAlertService {
    PagedResponse<SosAlertResponse> getAllByPhoneNumber(String phoneNumber, int page, int size);

    PagedResponse<SosAlertResponse> getAllSosAlerts(int page, int size);

    SosAlertResponse create(SosAlertRequest request, String phone);

    SosAlertResponse update(Long id, SosAlertRequest request);

    void delete(Long id);

    SosAlertResponse getById(Long id);

    void finish(Long id);

    void assignResponsible(Long id, Long userId);

    void reject(Long id);
}
