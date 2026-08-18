package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.TimeSlotRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TimeSlotResponse;
import com.brilliantsofts.EliteUniversity.entity.TimeSlot;

import java.time.LocalTime;

public class TimeSlotMapper {
    public static TimeSlot toEntity(TimeSlotRequest request) {
        TimeSlot entity = new TimeSlot();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setStartTime(LocalTime.parse(request.getStartTime()));
        entity.setEndTime(LocalTime.parse(request.getEndTime()));
        entity.setSlotType(request.getSlotType());
        entity.setDurationMinutes(request.getDurationMinutes());
        entity.setSortOrder(request.getSortOrder());
        entity.setActive(request.isActive());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static TimeSlotResponse toResponse(TimeSlot entity) {
        TimeSlotResponse response = new TimeSlotResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setStartTime(entity.getStartTime());
        response.setEndTime(entity.getEndTime());
        response.setSlotType(entity.getSlotType());
        response.setDurationMinutes(entity.getDurationMinutes());
        response.setSortOrder(entity.getSortOrder());
        response.setActive(entity.isActive());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
