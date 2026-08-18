package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegistrationConfigResponse {
    private Long id;
    private Long semesterId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer minCredits;
    private Integer maxCredits;
    private boolean allowAddDrop;
    private LocalDateTime addDropDeadline;
    private boolean advisorApprovalRequired;
    private boolean paymentRequired;
    private boolean isActive;
    private boolean isClosed;
    private String status;
    private String remarks;
    private LocalDateTime createdAt;
}
