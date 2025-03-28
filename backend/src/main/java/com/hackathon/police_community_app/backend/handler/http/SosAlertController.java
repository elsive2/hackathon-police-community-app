package com.hackathon.police_community_app.backend.handler.http;

import com.hackathon.police_community_app.backend.dto.request.SosAlertRequest;
import com.hackathon.police_community_app.backend.dto.response.PagedResponse;
import com.hackathon.police_community_app.backend.dto.response.SingleMessageResponse;
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
    @PreAuthorize("hasRole('POLICE')")
    public PagedResponse<SosAlertResponse> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getAllSosAlerts(page, size);
    }

    @GetMapping("/{phoneNumber}")
    @PreAuthorize("hasRole('POLICE')")
    public PagedResponse<SosAlertResponse> getAllByPhoneNumber(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable String phoneNumber) {
        return service.getAllByPhoneNumber(phoneNumber, page, size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('POLICE')")
    public SosAlertResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<SosAlertResponse> createSosAlert(@RequestBody SosAlertRequest request) {
        SosAlertResponse response = service.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('POLICE')")
    public SosAlertResponse update(
            @PathVariable Long id,
            @RequestBody SosAlertRequest request) {
        // @TODO: Сделать чтобы был ответсвенный пользователь
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('POLICE')")
    public void deleteSosAlert(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}/finish")
    @PreAuthorize("hasRole('POLICE')")
    public SingleMessageResponse finish(@PathVariable Long id) {
        service.finish(id);

        return new SingleMessageResponse("Status updated");
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('POLICE')")
    public SingleMessageResponse reject(@PathVariable Long id) {
        service.reject(id);

        return new SingleMessageResponse("Status updated");
    }

    @PatchMapping("/{id}/assign/{userId}")
    @PreAuthorize("hasRole('POLICE')")
    public SingleMessageResponse assignResponsible(
            @PathVariable Long id,
            @PathVariable Long userId
    ) {
        service.assignResponsible(id, userId);

        return new SingleMessageResponse("Assigned responsible");
    }

    // @TODO: Валидация
}
