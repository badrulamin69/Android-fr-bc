package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.NoticeRequest;
import com.brilliantsofts.EliteUniversity.dto.response.NoticeResponse;
import com.brilliantsofts.EliteUniversity.entity.Notice;

public class NoticeMapper {
    public static Notice toEntity(NoticeRequest request) {
        Notice entity = new Notice();
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setAttachmentUrl(request.getAttachmentUrl());
        entity.setPublishDate(request.getPublishDate());
        entity.setExpiryDate(request.getExpiryDate());
        entity.setPublished(request.isPublished());
        entity.setAudience(request.getAudience());
        return entity;
    }

    public static NoticeResponse toResponse(Notice entity) {
        NoticeResponse response = new NoticeResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setContent(entity.getContent());
        response.setAttachmentUrl(entity.getAttachmentUrl());
        response.setPublishDate(entity.getPublishDate());
        response.setExpiryDate(entity.getExpiryDate());
        response.setPublished(entity.isPublished());
        response.setAudience(entity.getAudience());
        if (entity.getCreatedBy() != null) {
            response.setCreatedById(entity.getCreatedBy().getId());
            response.setCreatedByName(entity.getCreatedBy().getUsername());
        }
        if (entity.getFaculty() != null) {
            response.setFacultyId(entity.getFaculty().getId());
            response.setFacultyName(entity.getFaculty().getName());
        }
        if (entity.getDepartment() != null) {
            response.setDepartmentId(entity.getDepartment().getId());
            response.setDepartmentName(entity.getDepartment().getName());
        }
        return response;
    }
}
