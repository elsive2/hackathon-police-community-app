package com.hackathon.police_community_app.backend.handler.http;

import com.hackathon.police_community_app.backend.dto.request.SosAlertRequest;
import com.hackathon.police_community_app.backend.dto.response.PagedResponse;
import com.hackathon.police_community_app.backend.dto.response.SosAlertResponse;
import com.hackathon.police_community_app.backend.service.SosAlertService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/sos-alert")
@AllArgsConstructor
@RestController
public class SosAlertController {
    private final SosAlertService service;

    @GetMapping
    public PagedResponse<SosAlertResponse> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getAllSosAlerts(page, size);
    }

    @GetMapping("/{phoneNumber}")
    public PagedResponse<SosAlertResponse> getAllByPhoneNumber(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable String phoneNumber) {
        return service.getAllByPhoneNumber(phoneNumber, page, size);
    }

    @GetMapping("/{id}")
    public SosAlertResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('CITIZEN')")
    public ResponseEntity<SosAlertResponse> createSosAlert(@RequestBody SosAlertRequest request) {
        SosAlertResponse response = service.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CITIZEN')")
    public SosAlertResponse updateSosAlert(
            @PathVariable Long id,
            @RequestBody SosAlertRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CITIZEN')")
    public void deleteSosAlert(@PathVariable Long id) {
        service.delete(id);
    }
}
