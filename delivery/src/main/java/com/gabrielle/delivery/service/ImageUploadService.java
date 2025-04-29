package com.gabrielle.delivery.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Unable to continue, file is empty");
        }

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = uploadDir + File.separator + file.getOriginalFilename();
        File serverFile = new File(filePath);

        try (FileOutputStream fos = new FileOutputStream(serverFile)) {
            fos.write(file.getBytes());
        }

        return file.getOriginalFilename();
    }

    public String getUploadDir() {
        return uploadDir;
    }
}
