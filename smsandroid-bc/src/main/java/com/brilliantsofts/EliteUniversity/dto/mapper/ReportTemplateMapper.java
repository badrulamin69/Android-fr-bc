package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ReportTemplateRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ReportTemplateResponse;
import com.brilliantsofts.EliteUniversity.entity.ReportTemplate;

public class ReportTemplateMapper {
    public static ReportTemplate toEntity(ReportTemplateRequest request) {
        ReportTemplate entity = new ReportTemplate();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setReportType(request.getReportType());
        entity.setTemplateConfig(request.getTemplateConfig());
        entity.setActive(request.isActive());
        return entity;
    }

    public static ReportTemplateResponse toResponse(ReportTemplate entity) {
        ReportTemplateResponse response = new ReportTemplateResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setDescription(entity.getDescription());
        response.setReportType(entity.getReportType());
        response.setTemplateConfig(entity.getTemplateConfig());
        response.setActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
