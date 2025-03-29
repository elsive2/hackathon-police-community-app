package com.hackathon.police_community_app.backend.handler.http;

import com.hackathon.police_community_app.backend.authentication.SmsCodeAuthenticationToken;
import com.hackathon.police_community_app.backend.dto.request.ArticleRequest;
import com.hackathon.police_community_app.backend.dto.response.PagedResponse;
import com.hackathon.police_community_app.backend.dto.response.SingleMessageResponse;
import com.hackathon.police_community_app.backend.dto.response.ArticleResponse;
import com.hackathon.police_community_app.backend.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "Article", description = "Статьи")
@RequestMapping("/api/articles")
@AllArgsConstructor
@RestController
public class ArticleController {
    private final ArticleService service;

    @Operation(summary = "Получение всех статей")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PagedResponse<ArticleResponse> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getAll(page, size);
    }

    @Operation(summary = "Получение всех статей по автору")
    @GetMapping("/author/{authorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public PagedResponse<ArticleResponse> getAllByAuthor(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable Long authorId) {
        return service.getByAuthorId(authorId, page, size);
    }

    @Operation(summary = "Получение статьи по идентификатору")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ArticleResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Сохранение статьи")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticleResponse> createSosAlert(
            @RequestBody @Valid ArticleRequest request,
            Principal principal
    ) {
        SmsCodeAuthenticationToken authentication = (SmsCodeAuthenticationToken) principal;

        ArticleResponse response = service.create(request, authentication.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Обновление статьи")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ArticleResponse update(
            @PathVariable Long id,
            @RequestBody @Valid ArticleRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Удаление статьи")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public SingleMessageResponse delete(@PathVariable Long id) {
        service.delete(id);
        return new SingleMessageResponse("Deleted");
    }
}
