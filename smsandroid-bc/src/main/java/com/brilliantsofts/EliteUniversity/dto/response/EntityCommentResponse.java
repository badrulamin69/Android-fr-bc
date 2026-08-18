package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EntityCommentResponse {
    private Long id;
    private String entityType;
    private Long entityId;
    private Long userId;
    private String content;
    private Long parentId;
    private boolean edited;
    private String status;
    private LocalDateTime createdAt;
}