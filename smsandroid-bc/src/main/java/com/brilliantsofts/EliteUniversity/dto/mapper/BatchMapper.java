package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.BatchRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BatchResponse;
import com.brilliantsofts.EliteUniversity.entity.Batch;

public class BatchMapper {
    public static Batch toEntity(BatchRequest request) {
        Batch entity = new Batch();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setStartYear(request.getStartYear());
        entity.setEndYear(request.getEndYear());
        entity.setCourseId(request.getCourseId());
        return entity;
    }

    public static BatchResponse toResponse(Batch entity) {
        BatchResponse response = new BatchResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setStartYear(entity.getStartYear());
        response.setEndYear(entity.getEndYear());
        response.setCourseId(entity.getCourseId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
