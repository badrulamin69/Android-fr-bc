package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AnnouncementRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AnnouncementResponse;
import com.brilliantsofts.EliteUniversity.entity.Announcement;

import java.util.UUID;

public class AnnouncementMapper {
    public static Announcement toEntity(AnnouncementRequest request) {
        Announcement entity = new Announcement();
        entity.setUniqueCode(UUID.randomUUID().toString());
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setType(request.getType());
        entity.setPostedBy(request.getPostedBy());
        entity.setActive(Boolean.TRUE.equals(request.getIsActive()));
        return entity;
    }

    public static AnnouncementResponse toResponse(Announcement entity) {
        AnnouncementResponse response = new AnnouncementResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTitle(entity.getTitle());
        response.setContent(entity.getContent());
        response.setType(entity.getType());
        response.setPostedBy(entity.getPostedBy());
        response.setActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
