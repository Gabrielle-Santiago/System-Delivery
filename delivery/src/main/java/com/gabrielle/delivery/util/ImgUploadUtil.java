package com.gabrielle.delivery.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Component
public class ImgUploadUtil {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public boolean uploadImg(MultipartFile img) {
        boolean successUpload = false;

        if (!img.isEmpty()) {
            String fileName = img.getOriginalFilename();
            try {
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs(); 
                }

                File serverFile = new File(dir, fileName);
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                    stream.write(img.getBytes());
                }

                successUpload = true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return successUpload;
    }
}
