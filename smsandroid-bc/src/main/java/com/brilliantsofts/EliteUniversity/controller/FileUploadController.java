package com.brilliantsofts.EliteUniversity.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Value("${app.upload.dir:./uploads}")
    private String uploadDir;

    @PostMapping("/{module}")
    public ResponseEntity<Map<String, String>> upload(
            @PathVariable String module,
            @RequestParam("file") MultipartFile file) throws IOException {
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path targetDir = Paths.get(uploadDir, module);
        if (!Files.exists(targetDir)) {
            Files.createDirectories(targetDir);
        }
        Path filePath = targetDir.resolve(filename);
        file.transferTo(filePath.toFile());

        Map<String, String> response = new HashMap<>();
        response.put("filename", filename);
        response.put("url", "/uploads/" + module + "/" + filename);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{module}/multiple")
    public ResponseEntity<List<Map<String, String>>> uploadMultiple(
            @PathVariable String module,
            @RequestParam("files") List<MultipartFile> files) throws IOException {
        List<Map<String, String>> results = new ArrayList<>();
        if (files != null) {
            for (MultipartFile file : files) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path targetDir = Paths.get(uploadDir, module);
                if (!Files.exists(targetDir)) {
                    Files.createDirectories(targetDir);
                }
                Path filePath = targetDir.resolve(filename);
                file.transferTo(filePath.toFile());

                Map<String, String> item = new HashMap<>();
                item.put("filename", filename);
                item.put("url", "/uploads/" + module + "/" + filename);
                results.add(item);
            }
        }
        return ResponseEntity.ok(results);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFile(@RequestParam(value = "url", required = false) String url) {
        if (url != null) {
            try {
                Path targetPath = Paths.get("." + url);
                if (Files.exists(targetPath)) {
                    Files.delete(targetPath);
                }
            } catch (Exception ignored) {
            }
        }
        return ResponseEntity.ok().build();
    }
}
