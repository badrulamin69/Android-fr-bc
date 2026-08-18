package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class EntityCommentRequest {
    private String entityType;
    private Long entityId;
    private Long userId;
    private String content;
    private Long parentId;
    private String status;
}