package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationResponse {
    private Long id;
    private String uniqueCode;
    private Long userId;
    private String title;
    private String message;
    private String type;
    private boolean isRead;
    private String referenceType;
    private Long referenceId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
