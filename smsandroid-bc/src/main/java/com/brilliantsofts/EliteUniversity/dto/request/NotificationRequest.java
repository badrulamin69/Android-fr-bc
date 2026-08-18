package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class NotificationRequest {
    private Long userId;
    private String title;
    private String message;
    private String type;
    private Boolean isRead;
    private String referenceType;
    private Long referenceId;
}
