package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AnnouncementRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AnnouncementResponse;
import com.brilliantsofts.EliteUniversity.dto.mapper.AnnouncementMapper;
import com.brilliantsofts.EliteUniversity.entity.Announcement;
import com.brilliantsofts.EliteUniversity.repository.AnnouncementRepository;
import com.brilliantsofts.EliteUniversity.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementRepository repository;

    @Override
    public AnnouncementResponse create(AnnouncementRequest request) {
        Announcement entity = AnnouncementMapper.toEntity(request);
        return AnnouncementMapper.toResponse(repository.save(entity));
    }
    @Override
    public AnnouncementResponse update(Long id, AnnouncementRequest request) {
        Announcement entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Announcement not found"));
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setType(request.getType());
        entity.setPostedBy(request.getPostedBy());
        entity.setActive(Boolean.TRUE.equals(request.getIsActive()));
        return AnnouncementMapper.toResponse(repository.save(entity));
    }
    @Override
    public AnnouncementResponse getById(Long id) {
        return AnnouncementMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Announcement not found")));
    }
    @Override
    public Page<AnnouncementResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(AnnouncementMapper::toResponse);
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
