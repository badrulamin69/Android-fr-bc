package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class MessageRequest {
    private Long senderId;
    private Long receiverId;
    private String subject;
    private String body;
}
