package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChoiceFillingConfigResponse {
    private Long id;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
