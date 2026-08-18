package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.BookIssue;
import com.brilliantsofts.EliteUniversity.enums.BookIssueStatus;

import java.util.List;

public interface BookIssueRepository extends org.springframework.data.jpa.repository.JpaRepository<BookIssue, Long> {
    List<BookIssue> findByStudentId(Long studentId);
    List<BookIssue> findByBookId(Long bookId);
    List<BookIssue> findByStatus(BookIssueStatus status);
}
