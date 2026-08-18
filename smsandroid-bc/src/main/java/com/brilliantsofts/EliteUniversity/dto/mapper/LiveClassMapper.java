package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.LiveClassRequest;
import com.brilliantsofts.EliteUniversity.dto.response.LiveClassResponse;
import com.brilliantsofts.EliteUniversity.entity.LiveClass;

public class LiveClassMapper {
    public static LiveClass toEntity(LiveClassRequest request) {
        LiveClass entity = new LiveClass();
        entity.setTitle(request.getTitle());
        entity.setMeetingUrl(request.getMeetingUrl());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        return entity;
    }

    public static LiveClassResponse toResponse(LiveClass entity) {
        LiveClassResponse response = new LiveClassResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setMeetingUrl(entity.getMeetingUrl());
        response.setStartTime(entity.getStartTime());
        response.setEndTime(entity.getEndTime());
        if (entity.getModule() != null) {
            response.setModuleId(entity.getModule().getId());
            response.setModuleTitle(entity.getModule().getModuleTitle());
        }
        if (entity.getTeacher() != null) {
            response.setTeacherId(entity.getTeacher().getId());
            response.setTeacherName(entity.getTeacher().getFullName());
        }
        return response;
    }
}
