package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EntityAttachmentResponse {
    private Long id;
    private String entityType;
    private Long entityId;
    private String originalFilename;
    private String storedFilename;
    private String path;
    private String contentType;
    private Long size;
    private Long uploadedById;
    private String category;
    private boolean verified;
    private String status;
    private String formattedSize;
    private LocalDateTime createdAt;
}