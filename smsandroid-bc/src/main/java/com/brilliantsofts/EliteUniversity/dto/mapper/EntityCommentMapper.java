package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.EntityCommentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EntityCommentResponse;
import com.brilliantsofts.EliteUniversity.entity.EntityComment;

public class EntityCommentMapper {
    public static EntityComment toEntity(EntityCommentRequest request) {
        EntityComment entity = new EntityComment();
        entity.setEntityType(request.getEntityType());
        entity.setEntityId(request.getEntityId());
        entity.setUserId(request.getUserId());
        entity.setContent(request.getContent());
        entity.setParentId(request.getParentId());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static EntityCommentResponse toResponse(EntityComment entity) {
        EntityCommentResponse response = new EntityCommentResponse();
        response.setId(entity.getId());
        response.setEntityType(entity.getEntityType());
        response.setEntityId(entity.getEntityId());
        response.setUserId(entity.getUserId());
        response.setContent(entity.getContent());
        response.setParentId(entity.getParentId());
        response.setEdited(entity.isEdited());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }
}