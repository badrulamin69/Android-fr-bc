package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.MessageRequest;
import com.brilliantsofts.EliteUniversity.dto.response.MessageResponse;
import com.brilliantsofts.EliteUniversity.entity.Message;

import java.util.UUID;

public class MessageMapper {
    public static Message toEntity(MessageRequest request) {
        Message entity = new Message();
        entity.setUniqueCode(UUID.randomUUID().toString());
        entity.setSubject(request.getSubject());
        entity.setBody(request.getBody());
        return entity;
    }

    public static MessageResponse toResponse(Message entity) {
        MessageResponse response = new MessageResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getSender() != null) {
            response.setSenderId(entity.getSender().getId());
            response.setSenderName(entity.getSender().getUsername());
        }
        if (entity.getReceiver() != null) {
            response.setReceiverId(entity.getReceiver().getId());
            response.setReceiverName(entity.getReceiver().getUsername());
        }
        response.setSubject(entity.getSubject());
        response.setBody(entity.getBody());
        response.setRead(entity.isRead());
        response.setReadAt(entity.getReadAt());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
