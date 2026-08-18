package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookIssueRequest {
    private LocalDate issueDate;
    private LocalDate dueDate;
    private Long bookId;
    private Long studentId;
}
