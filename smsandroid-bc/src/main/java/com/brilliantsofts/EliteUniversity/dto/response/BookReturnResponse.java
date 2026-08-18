package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BookReturnResponse {
    private Long id;
    private String uniqueCode;
    private Long bookIssueId;
    private LocalDate returnDate;
    private BigDecimal fineAmount;
    private Boolean finePaid;
    private String conditionAtReturn;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
