package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.BookReturnRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BookReturnResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookReturnService {
    BookReturnResponse create(BookReturnRequest request);
    BookReturnResponse update(Long id, BookReturnRequest request);
    BookReturnResponse getById(Long id);
    Page<BookReturnResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
