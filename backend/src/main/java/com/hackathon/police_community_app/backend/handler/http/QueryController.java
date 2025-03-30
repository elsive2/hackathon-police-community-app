package com.hackathon.police_community_app.backend.handler.http;

import com.hackathon.police_community_app.backend.authentication.SmsCodeAuthenticationToken;
import com.hackathon.police_community_app.backend.dto.request.QueryRequest;
import com.hackathon.police_community_app.backend.dto.response.PagedResponse;
import com.hackathon.police_community_app.backend.dto.response.QueryResponse;
import com.hackathon.police_community_app.backend.dto.response.SingleMessageResponse;
import com.hackathon.police_community_app.backend.service.QueryService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@Tag(name = "Query", description = "Заявки о происшествии")
@RequestMapping("/api/queries")
@AllArgsConstructor
@RestController
public class QueryController {
    private final QueryService service;

    @Operation(summary = "Получение всех заявок")
    @GetMapping
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public PagedResponse<QueryResponse> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getAll(page, size);
    }

    @Operation(summary = "Получение всех заявок по текущему пользователю")
    @GetMapping("/my")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public PagedResponse<QueryResponse> getAllCurrent(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Principal principal
    ) {
        SmsCodeAuthenticationToken authentication = (SmsCodeAuthenticationToken) principal;

        return service.getAllByUser(authentication.getUserId(), page, size);
    }

    @Operation(summary = "Получение заявки по идентификатору")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public QueryResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @RateLimiter(name = "myService")
    @Operation(summary = "Сохранение заявки")
    @PostMapping
    public ResponseEntity<QueryResponse> create(@RequestBody QueryRequest request, Principal principal) {
        SmsCodeAuthenticationToken authentication = (SmsCodeAuthenticationToken) principal;

        QueryResponse response = service.create(request, authentication.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Обновление заявки")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public QueryResponse update(
            @PathVariable Long id,
            @RequestBody QueryRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Удаление заявки")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "Обновить статус до завершения заявки")
    @PutMapping("/{id}/finish")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public SingleMessageResponse finish(@PathVariable Long id) {
        service.finish(id);

        return new SingleMessageResponse("Status updated");
    }

    @Operation(summary = "Обновить статус до отклонения заявки")
    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('POLICE') || hasRole('ADMIN')")
    public SingleMessageResponse reject(@PathVariable Long id) {
        service.reject(id);

        return new SingleMessageResponse("Status updated");
    }

    @Operation(summary = "Назначение авторизованного пользователя как ответственного за заявку")
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
}
