package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.EntityAttachmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EntityAttachmentResponse;
import com.brilliantsofts.EliteUniversity.service.EntityAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/attachments")
@RequiredArgsConstructor
public class EntityAttachmentController {

    private final EntityAttachmentService service;

    @PostMapping("/upload")
    public ResponseEntity<EntityAttachmentResponse> upload(
            @RequestParam String entityType,
            @RequestParam Long entityId,
            @RequestParam(required = false) String category,
            @RequestBody EntityAttachmentRequest request) {
        request.setEntityType(entityType);
        request.setEntityId(entityId);
        if (category != null) request.setCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<EntityAttachmentResponse>> getAll(
            @RequestParam String entityType,
            @RequestParam Long entityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getByEntityTypeAndEntityId(entityType, entityId, pageable));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> count(
            @RequestParam String entityType,
            @RequestParam Long entityId) {
        return ResponseEntity.ok(Map.of("count", service.countByEntityTypeAndEntityId(entityType, entityId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityAttachmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}