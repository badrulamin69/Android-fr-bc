package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AcademicCalendarRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicCalendarResponse;
import com.brilliantsofts.EliteUniversity.entity.AcademicCalendar;

public class AcademicCalendarMapper {
    public static AcademicCalendar toEntity(AcademicCalendarRequest request) {
        AcademicCalendar entity = new AcademicCalendar();
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setEventType(request.getEventType());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setSemesterId(request.getSemesterId());
        entity.setHoliday(request.isHoliday());
        entity.setPublished(request.isPublished());
        entity.setColor(request.getColor());
        return entity;
    }

    public static AcademicCalendarResponse toResponse(AcademicCalendar entity) {
        AcademicCalendarResponse response = new AcademicCalendarResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setEventType(entity.getEventType());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setSemesterId(entity.getSemesterId());
        response.setHoliday(entity.isHoliday());
        response.setPublished(entity.isPublished());
        response.setColor(entity.getColor());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
