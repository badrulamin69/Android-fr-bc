package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.MessageRequest;
import com.brilliantsofts.EliteUniversity.dto.response.MessageResponse;
import com.brilliantsofts.EliteUniversity.dto.mapper.MessageMapper;
import com.brilliantsofts.EliteUniversity.entity.Message;
import com.brilliantsofts.EliteUniversity.repository.MessageRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository repository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public MessageResponse create(MessageRequest request) {
        Message entity = MessageMapper.toEntity(request);
        if (request.getSenderId() != null) entity.setSender(userRepository.findById(request.getSenderId()).orElse(null));
        if (request.getReceiverId() != null) entity.setReceiver(userRepository.findById(request.getReceiverId()).orElse(null));
        return MessageMapper.toResponse(repository.save(entity));
    }
    @Override
    public MessageResponse update(Long id, MessageRequest request) {
        Message entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Message not found"));
        entity.setSubject(request.getSubject());
        entity.setBody(request.getBody());
        if (request.getSenderId() != null) entity.setSender(userRepository.findById(request.getSenderId()).orElse(null));
        if (request.getReceiverId() != null) entity.setReceiver(userRepository.findById(request.getReceiverId()).orElse(null));
        return MessageMapper.toResponse(repository.save(entity));
    }
    @Override
    public MessageResponse getById(Long id) {
        return MessageMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Message not found")));
    }
    @Override
    public Page<MessageResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(MessageMapper::toResponse);
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
