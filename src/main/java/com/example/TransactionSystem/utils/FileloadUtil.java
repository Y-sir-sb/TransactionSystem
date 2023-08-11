package com.example.TransactionSystem.utils;

import java.io.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class FileloadUtil{
    public boolean uploadFile(MultipartFile file, String uploadDirectory) {
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                file.transferTo(new File(uploadDirectory + "/" + fileName));
                return true; // Upload success
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false; // Upload failure
    }
    public static ResponseEntity<byte[]> downloadFile(String fileName, byte[] fileContent) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);

            return ResponseEntity.ok().headers(headers).body(fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().body(null);
    }

}
