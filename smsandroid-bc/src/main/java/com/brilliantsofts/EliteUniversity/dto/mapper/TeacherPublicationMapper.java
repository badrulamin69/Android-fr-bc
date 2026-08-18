package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.TeacherPublicationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TeacherPublicationResponse;
import com.brilliantsofts.EliteUniversity.entity.TeacherPublication;

public class TeacherPublicationMapper {
    public static TeacherPublication toEntity(TeacherPublicationRequest request) {
        TeacherPublication entity = new TeacherPublication();
        entity.setTeacherId(request.getTeacherId());
        entity.setTitle(request.getTitle());
        entity.setAuthors(request.getAuthors());
        entity.setJournal(request.getJournal());
        entity.setPublicationType(request.getPublicationType());
        entity.setPublicationDate(request.getPublicationDate());
        entity.setVolume(request.getVolume());
        entity.setIssue(request.getIssue());
        entity.setPages(request.getPages());
        entity.setDoi(request.getDoi());
        entity.setIsbn(request.getIsbn());
        entity.setAbstractText(request.getAbstractText());
        entity.setUrl(request.getUrl());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static TeacherPublicationResponse toResponse(TeacherPublication entity) {
        TeacherPublicationResponse response = new TeacherPublicationResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTeacherId(entity.getTeacherId());
        response.setTitle(entity.getTitle());
        response.setAuthors(entity.getAuthors());
        response.setJournal(entity.getJournal());
        response.setPublicationType(entity.getPublicationType());
        response.setPublicationDate(entity.getPublicationDate());
        response.setVolume(entity.getVolume());
        response.setIssue(entity.getIssue());
        response.setPages(entity.getPages());
        response.setDoi(entity.getDoi());
        response.setIsbn(entity.getIsbn());
        response.setAbstractText(entity.getAbstractText());
        response.setUrl(entity.getUrl());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
