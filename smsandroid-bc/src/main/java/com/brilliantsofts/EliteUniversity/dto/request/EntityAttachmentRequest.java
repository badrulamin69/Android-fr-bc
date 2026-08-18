package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class EntityAttachmentRequest {
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
}