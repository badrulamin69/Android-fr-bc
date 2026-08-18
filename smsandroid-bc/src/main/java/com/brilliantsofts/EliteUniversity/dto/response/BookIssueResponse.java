package com.brilliantsofts.EliteUniversity.dto.response;

import com.brilliantsofts.EliteUniversity.enums.BookIssueStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookIssueResponse {
    private Long id;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Double fine;
    private BookIssueStatus status;
    private Long bookId;
    private String bookTitle;
    private Long studentId;
    private String studentName;
}
