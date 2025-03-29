package com.hackathon.police_community_app.backend.service;

import com.hackathon.police_community_app.backend.dto.response.FileUploadResponse;
import com.hackathon.police_community_app.backend.exception.FileSizeExceededException;
import com.hackathon.police_community_app.backend.exception.InvalidFileSignatureException;
import com.hackathon.police_community_app.backend.exception.UnsupportedFileTypeException;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Set;

public interface UploadService {
    FileUploadResponse uploadFile(MultipartFile file);

    default void validateFile(MultipartFile file) {
        // Проверка размера файла (макс. 10MB)
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new FileSizeExceededException();
        }

        Set<String> allowedTypes = Set.of(
                "image/jpeg", "image/png", "image/webp",
                "application/pdf", "text/plain"
        );

        if (!allowedTypes.contains(file.getContentType())) {
            throw new UnsupportedFileTypeException();
        }

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        Set<String> allowedExtensions = Set.of("jpg", "jpeg", "png", "pdf", "txt");

        if (extension == null || !allowedExtensions.contains(extension.toLowerCase())) {
            throw new UnsupportedFileTypeException();
        }
    }

    // Простая проверка "магических чисел" файла
    @SneakyThrows
    default void checkForMaliciousContent(MultipartFile file) {
        byte[] header = new byte[4];
        try (InputStream is = file.getInputStream()) {
            if (is.read(header) != -1) {
                // JPEG: FF D8 FF E0
                if (header[0] == (byte) 0xFF && header[1] == (byte) 0xD8) {
                    return;
                }
                // PNG: 89 50 4E 47
                if (header[0] == (byte) 0x89 && header[1] == 0x50) {
                    return;
                }
                throw new InvalidFileSignatureException();
            }
        }
    }

}
