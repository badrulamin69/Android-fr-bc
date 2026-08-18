package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.MessageRequest;
import com.brilliantsofts.EliteUniversity.dto.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {
    MessageResponse create(MessageRequest request);
    MessageResponse update(Long id, MessageRequest request);
    MessageResponse getById(Long id);
    Page<MessageResponse> getAll(Pageable pageable);
    void delete(Long id);
}
