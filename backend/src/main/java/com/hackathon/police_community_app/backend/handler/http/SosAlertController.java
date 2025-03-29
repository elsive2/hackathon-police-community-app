package com.hackathon.police_community_app.backend.handler.http;

import com.hackathon.police_community_app.backend.authentication.SmsCodeAuthenticationToken;
import com.hackathon.police_community_app.backend.dto.request.SosAlertRequest;
import com.hackathon.police_community_app.backend.dto.response.PagedResponse;
import com.hackathon.police_community_app.backend.dto.response.SingleMessageResponse;
import com.hackathon.police_community_app.backend.dto.response.SosAlertResponse;
import com.hackathon.police_community_app.backend.service.SosAlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@Tag(name = "Sos Alert", description = "SOS Тревога")
@RequestMapping("/api/sos-alerts")
@AllArgsConstructor
@RestController
public class SosAlertController {
    private final SosAlertService service;

    @Operation(summary = "Получение всех SOS-алертов")
    @GetMapping
    @PreAuthorize("hasRole('POLICE')")
    public PagedResponse<SosAlertResponse> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getAllSosAlerts(page, size);
    }

    @Operation(summary = "Получение всех SOS-алертов по номеру телефона")
    @GetMapping("/phone/{phoneNumber}")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public PagedResponse<SosAlertResponse> getAllByPhoneNumber(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable String phoneNumber) {
        return service.getAllByPhoneNumber(phoneNumber, page, size);
    }

    @Operation(summary = "Получение SOS-алерта по идентификатору")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public SosAlertResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Сохранение SOS-алерта")
    @PostMapping
    public ResponseEntity<SosAlertResponse> createSosAlert(@RequestBody SosAlertRequest request, Principal principal) {
        SmsCodeAuthenticationToken authentication = (SmsCodeAuthenticationToken) principal;
        String phone = Objects.isNull(authentication) ? null : authentication.getPhoneNumber();

        SosAlertResponse response = service.create(request, phone);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Обновление SOS-алерта")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public SosAlertResponse update(
            @PathVariable Long id,
            @RequestBody SosAlertRequest request) {
        // @TODO: Сделать чтобы был ответсвенный пользователь
        return service.update(id, request);
    }

    @Operation(summary = "Удаление SOS-алерта")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public void deleteSosAlert(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "Обновить статус до завершения SOS-алерта")
    @PutMapping("/{id}/finish")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public SingleMessageResponse finish(@PathVariable Long id) {
        service.finish(id);

        return new SingleMessageResponse("Status updated");
    }

    @Operation(summary = "Обновить статус до отклонения SOS-алерта")
    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public SingleMessageResponse reject(@PathVariable Long id) {
        service.reject(id);

        return new SingleMessageResponse("Status updated");
    }

    @Operation(summary = "Назначение авторизованного пользователя как ответственного за SOS-алерта")
    @PutMapping("/{id}/assign")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public SingleMessageResponse assignResponsible(
            @PathVariable Long id,
            Principal principal
    ) {
        SmsCodeAuthenticationToken authentication = (SmsCodeAuthenticationToken) principal;

        service.assignResponsible(id, authentication.getUserId());

        return new SingleMessageResponse("Assigned responsible");
    }

    // @TODO: Валидация
}
