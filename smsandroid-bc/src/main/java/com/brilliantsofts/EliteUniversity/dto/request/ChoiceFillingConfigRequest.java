package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChoiceFillingConfigRequest {
    private Long sessionId;
    private LocalDateTime choiceStartDate;
    private LocalDateTime choiceEndDate;
    private Integer maxChoices;
    private Integer minChoices;
    private Boolean allowEditingBeforeDeadline;
    private Boolean autoLockAfterDeadline;
    private Boolean includeWaitingList;
    private String status;
    private String remarks;
    private Boolean isActive;
}
