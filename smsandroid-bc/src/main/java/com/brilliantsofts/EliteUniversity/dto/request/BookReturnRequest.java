package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookReturnRequest {
    private Long bookIssueId;
    private LocalDate returnDate;
    private BigDecimal fineAmount;
    private Boolean finePaid;
    private String conditionAtReturn;
    private String remarks;
}
