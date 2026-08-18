package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.EntityAttachmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EntityAttachmentResponse;
import com.brilliantsofts.EliteUniversity.entity.EntityAttachment;

public class EntityAttachmentMapper {
    public static EntityAttachment toEntity(EntityAttachmentRequest request) {
        EntityAttachment entity = new EntityAttachment();
        entity.setEntityType(request.getEntityType());
        entity.setEntityId(request.getEntityId());
        entity.setOriginalFilename(request.getOriginalFilename());
        entity.setStoredFilename(request.getStoredFilename());
        entity.setPath(request.getPath());
        entity.setContentType(request.getContentType());
        entity.setSize(request.getSize());
        entity.setUploadedById(request.getUploadedById());
        entity.setCategory(request.getCategory());
        entity.setVerified(request.isVerified());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static EntityAttachmentResponse toResponse(EntityAttachment entity) {
        EntityAttachmentResponse response = new EntityAttachmentResponse();
        response.setId(entity.getId());
        response.setEntityType(entity.getEntityType());
        response.setEntityId(entity.getEntityId());
        response.setOriginalFilename(entity.getOriginalFilename());
        response.setStoredFilename(entity.getStoredFilename());
        response.setPath(entity.getPath());
        response.setContentType(entity.getContentType());
        response.setSize(entity.getSize());
        response.setUploadedById(entity.getUploadedById());
        response.setCategory(entity.getCategory());
        response.setVerified(entity.isVerified());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        if (entity.getSize() != null) {
            if (entity.getSize() < 1024) response.setFormattedSize(entity.getSize() + " B");
            else if (entity.getSize() < 1048576) response.setFormattedSize(String.format("%.1f KB", entity.getSize() / 1024.0));
            else response.setFormattedSize(String.format("%.1f MB", entity.getSize() / 1048576.0));
        }
        return response;
    }
}