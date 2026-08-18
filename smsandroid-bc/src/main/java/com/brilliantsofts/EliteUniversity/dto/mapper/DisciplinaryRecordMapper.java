package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.DisciplinaryRecordRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DisciplinaryRecordResponse;
import com.brilliantsofts.EliteUniversity.entity.DisciplinaryRecord;

import java.util.UUID;

public class DisciplinaryRecordMapper {
    public static DisciplinaryRecord toEntity(DisciplinaryRecordRequest request) {
        DisciplinaryRecord entity = new DisciplinaryRecord();
        entity.setUniqueCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setIncidentDate(request.getIncidentDate());
        entity.setCategory(request.getCategory());
        entity.setSeverity(request.getSeverity());
        entity.setDescription(request.getDescription());
        entity.setActionTaken(request.getActionTaken());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static DisciplinaryRecordResponse toResponse(DisciplinaryRecord entity) {
        DisciplinaryRecordResponse response = new DisciplinaryRecordResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
        }
        response.setIncidentDate(entity.getIncidentDate());
        response.setCategory(entity.getCategory());
        response.setSeverity(entity.getSeverity());
        response.setDescription(entity.getDescription());
        response.setActionTaken(entity.getActionTaken());
        if (entity.getReportedBy() != null) {
            response.setReportedById(entity.getReportedBy().getId());
        }
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        return response;
    }
}
