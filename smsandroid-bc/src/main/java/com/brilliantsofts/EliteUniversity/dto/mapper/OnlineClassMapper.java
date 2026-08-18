package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.OnlineClassRequest;
import com.brilliantsofts.EliteUniversity.dto.response.OnlineClassResponse;
import com.brilliantsofts.EliteUniversity.entity.OnlineClass;

public class OnlineClassMapper {
    public static OnlineClass toEntity(OnlineClassRequest request) {
        OnlineClass entity = new OnlineClass();
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setMeetingUrl(request.getMeetingUrl());
        entity.setClassDate(request.getClassDate());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setRecordingUrl(request.getRecordingUrl());
        return entity;
    }

    public static OnlineClassResponse toResponse(OnlineClass entity) {
        OnlineClassResponse response = new OnlineClassResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setMeetingUrl(entity.getMeetingUrl());
        response.setClassDate(entity.getClassDate());
        response.setStartTime(entity.getStartTime());
        response.setEndTime(entity.getEndTime());
        response.setRecordingUrl(entity.getRecordingUrl());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getCourse() != null) {
            response.setCourseId(entity.getCourse().getId());
            response.setCourseName(entity.getCourse().getCourseName());
        }
        if (entity.getSubject() != null) {
            response.setSubjectId(entity.getSubject().getId());
            response.setSubjectName(entity.getSubject().getName());
        }
        if (entity.getAdministration() != null) {
            response.setAdministrationId(entity.getAdministration().getId());
            response.setAdministrationName(entity.getAdministration().getFullName());
        }
        return response;
    }
}
