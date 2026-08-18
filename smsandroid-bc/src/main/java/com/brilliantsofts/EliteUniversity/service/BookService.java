package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.BookRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookResponse create(BookRequest request);
    BookResponse update(Long id, BookRequest request);
    BookResponse getById(Long id);
    BookResponse getByIsbn(String isbn);
    Page<BookResponse> getAll(Pageable pageable);
    List<BookResponse> searchByTitle(String title);
    void delete(Long id);
}
