package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.GeneratedReportRequest;
import com.brilliantsofts.EliteUniversity.dto.response.GeneratedReportResponse;
import com.brilliantsofts.EliteUniversity.entity.GeneratedReport;
import java.time.LocalDateTime;

public class GeneratedReportMapper {
    public static GeneratedReport toEntity(GeneratedReportRequest request) {
        GeneratedReport entity = new GeneratedReport();
        entity.setTemplateId(request.getTemplateId());
        entity.setTitle(request.getTitle());
        entity.setReportType(request.getReportType());
        entity.setParameters(request.getParameters());
        entity.setFileUrl(request.getFileUrl());
        entity.setFormat(request.getFormat());
        entity.setGeneratedBy(request.getGeneratedBy());
        entity.setGeneratedAt(LocalDateTime.now());
        return entity;
    }

    public static GeneratedReportResponse toResponse(GeneratedReport entity) {
        GeneratedReportResponse response = new GeneratedReportResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTemplateId(entity.getTemplateId());
        response.setTitle(entity.getTitle());
        response.setReportType(entity.getReportType());
        response.setParameters(entity.getParameters());
        response.setFileUrl(entity.getFileUrl());
        response.setFormat(entity.getFormat());
        response.setGeneratedBy(entity.getGeneratedBy());
        response.setGeneratedAt(entity.getGeneratedAt());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}