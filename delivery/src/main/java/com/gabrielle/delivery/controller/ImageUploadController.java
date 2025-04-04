package com.gabrielle.delivery.controller;

import com.gabrielle.delivery.service.ImageUploadService;
import com.gabrielle.delivery.errorResponse.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "*")
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    public ImageUploadController(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = imageUploadService.uploadImage(file);
            return ResponseEntity.ok().body("Image saved in: " + filePath);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error uploading: " + e.getMessage()));
        }
    }
}
