package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.BookRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BookResponse;
import com.brilliantsofts.EliteUniversity.entity.Book;

public class BookMapper {
    public static Book toEntity(BookRequest request) {
        Book entity = new Book();
        entity.setTitle(request.getTitle());
        entity.setIsbn(request.getIsbn());
        entity.setAuthor(request.getAuthor());
        entity.setQuantity(request.getQuantity());
        entity.setAvailableQuantity(request.getAvailableQuantity());
        return entity;
    }

    public static BookResponse toResponse(Book entity) {
        BookResponse response = new BookResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setIsbn(entity.getIsbn());
        response.setAuthor(entity.getAuthor());
        response.setQuantity(entity.getQuantity());
        response.setAvailableQuantity(entity.getAvailableQuantity());
        return response;
    }
}
