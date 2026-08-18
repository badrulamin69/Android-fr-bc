package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.BookIssueRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BookIssueResponse;
import com.brilliantsofts.EliteUniversity.entity.BookIssue;

public class BookIssueMapper {
    public static BookIssue toEntity(BookIssueRequest request) {
        BookIssue entity = new BookIssue();
        entity.setIssueDate(request.getIssueDate());
        entity.setDueDate(request.getDueDate());
        return entity;
    }

    public static BookIssueResponse toResponse(BookIssue entity) {
        BookIssueResponse response = new BookIssueResponse();
        response.setId(entity.getId());
        response.setIssueDate(entity.getIssueDate());
        response.setDueDate(entity.getDueDate());
        response.setReturnDate(entity.getReturnDate());
        response.setFine(entity.getFine());
        response.setStatus(entity.getStatus());
        if (entity.getBook() != null) {
            response.setBookId(entity.getBook().getId());
            response.setBookTitle(entity.getBook().getTitle());
        }
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
            response.setStudentName(entity.getStudent().getFullName());
        }
        return response;
    }
}
