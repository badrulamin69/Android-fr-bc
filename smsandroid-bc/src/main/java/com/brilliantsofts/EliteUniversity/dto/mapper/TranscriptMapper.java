package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.TranscriptRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TranscriptResponse;
import com.brilliantsofts.EliteUniversity.entity.Transcript;

import java.time.LocalDateTime;
import java.util.UUID;

public class TranscriptMapper {
    public static Transcript toEntity(TranscriptRequest request) {
        Transcript entity = new Transcript();
        entity.setUniqueCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setTranscriptNumber(request.getTranscriptNumber());
        entity.setIssuedAt(LocalDateTime.now());
        entity.setStatus(request.getStatus());
        entity.setGpa(request.getGpa());
        entity.setTotalCredits(request.getTotalCredits());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static TranscriptResponse toResponse(Transcript entity) {
        TranscriptResponse response = new TranscriptResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTranscriptNumber(entity.getTranscriptNumber());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
        }
        if (entity.getProgram() != null) {
            response.setProgramId(entity.getProgram().getId());
        }
        if (entity.getSemester() != null) {
            response.setSemesterId(entity.getSemester().getId());
        }
        response.setIssuedAt(entity.getIssuedAt());
        response.setStatus(entity.getStatus());
        response.setGpa(entity.getGpa());
        response.setTotalCredits(entity.getTotalCredits());
        response.setRemarks(entity.getRemarks());
        if (entity.getIssuedBy() != null) {
            response.setIssuedById(entity.getIssuedBy().getId());
        }
        return response;
    }
}
