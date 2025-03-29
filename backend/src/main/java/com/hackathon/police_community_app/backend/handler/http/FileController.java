package com.hackathon.police_community_app.backend.handler.http;

import com.hackathon.police_community_app.backend.dto.response.FileUploadResponse;
import com.hackathon.police_community_app.backend.service.UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "File", description = "Работа с файлами")
@RestController
@AllArgsConstructor
@RequestMapping("/api/files")
public class FileController {
    private final UploadService uploadService;

    @Operation(summary = "Загрузка файла и получение ссылки")
    @PostMapping("/upload")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return uploadService.uploadFile(file);
    }
}
