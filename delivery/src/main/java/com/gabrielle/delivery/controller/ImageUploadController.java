package com.gabrielle.delivery.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gabrielle.delivery.errorResponse.ErrorResponse;
import com.gabrielle.delivery.service.ImageUploadService;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class ImageUploadController {

    private final ImageUploadService service;

    public ImageUploadController(ImageUploadService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = service.uploadImage(file);
            Path path = Paths.get("delivery/src/main/resources/static/images/uploads", fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok(Map.of("fileName", fileName));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error uploading: " + e.getMessage()));
        }
    }

    
    @GetMapping("/view/{fileName:.+}")
    public ResponseEntity<Resource> viewImage(@PathVariable String fileName) {
        try {
            Path imagePath = Paths.get(service.getUploadDir()).resolve(fileName).normalize();
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) 
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
