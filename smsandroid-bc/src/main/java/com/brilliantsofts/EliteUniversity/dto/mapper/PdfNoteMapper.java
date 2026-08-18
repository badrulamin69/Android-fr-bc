package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.PdfNoteRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PdfNoteResponse;
import com.brilliantsofts.EliteUniversity.entity.PdfNote;

public class PdfNoteMapper {
    public static PdfNote toEntity(PdfNoteRequest request) {
        PdfNote entity = new PdfNote();
        entity.setTitle(request.getTitle());
        entity.setFileUrl(request.getFileUrl());
        return entity;
    }

    public static PdfNoteResponse toResponse(PdfNote entity) {
        PdfNoteResponse response = new PdfNoteResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setFileUrl(entity.getFileUrl());
        if (entity.getModule() != null) {
            response.setModuleId(entity.getModule().getId());
            response.setModuleTitle(entity.getModule().getModuleTitle());
        }
        return response;
    }
}
