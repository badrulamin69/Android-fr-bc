package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.BookCategoryRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BookCategoryResponse;
import com.brilliantsofts.EliteUniversity.entity.BookCategory;

public class BookCategoryMapper {
    public static BookCategory toEntity(BookCategoryRequest request) {
        BookCategory entity = new BookCategory();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public static BookCategoryResponse toResponse(BookCategory entity) {
        BookCategoryResponse response = new BookCategoryResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setDescription(entity.getDescription());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
