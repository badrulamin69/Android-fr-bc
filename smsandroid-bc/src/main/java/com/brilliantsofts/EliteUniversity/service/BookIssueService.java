package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.BookIssueRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BookIssueResponse;
import com.brilliantsofts.EliteUniversity.enums.BookIssueStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookIssueService {
    BookIssueResponse issueBook(BookIssueRequest request);
    BookIssueResponse returnBook(Long issueId);
    BookIssueResponse getById(Long id);
    Page<BookIssueResponse> getAll(Pageable pageable);
    List<BookIssueResponse> getByStudent(Long studentId);
    List<BookIssueResponse> getByBook(Long bookId);
    List<BookIssueResponse> getByStatus(BookIssueStatus status);
    void delete(Long id);
}
