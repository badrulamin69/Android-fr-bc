package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.BookReturnRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BookReturnResponse;
import com.brilliantsofts.EliteUniversity.entity.BookReturn;

public class BookReturnMapper {
    public static BookReturn toEntity(BookReturnRequest request) {
        BookReturn entity = new BookReturn();
        entity.setReturnDate(request.getReturnDate());
        entity.setFineAmount(request.getFineAmount());
        entity.setFinePaid(Boolean.TRUE.equals(request.getFinePaid()));
        entity.setConditionAtReturn(request.getConditionAtReturn());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static BookReturnResponse toResponse(BookReturn entity) {
        BookReturnResponse response = new BookReturnResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getBookIssue() != null) {
            response.setBookIssueId(entity.getBookIssue().getId());
        }
        response.setReturnDate(entity.getReturnDate());
        response.setFineAmount(entity.getFineAmount());
        response.setFinePaid(entity.isFinePaid());
        response.setConditionAtReturn(entity.getConditionAtReturn());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
