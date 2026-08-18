package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegistrationConfigRequest {
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
    private String remarks;
}
