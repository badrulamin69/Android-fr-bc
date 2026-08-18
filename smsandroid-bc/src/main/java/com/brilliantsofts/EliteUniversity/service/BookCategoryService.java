package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.BookCategoryRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BookCategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookCategoryService {
    BookCategoryResponse create(BookCategoryRequest request);
    BookCategoryResponse update(Long id, BookCategoryRequest request);
    BookCategoryResponse getById(Long id);
    Page<BookCategoryResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
