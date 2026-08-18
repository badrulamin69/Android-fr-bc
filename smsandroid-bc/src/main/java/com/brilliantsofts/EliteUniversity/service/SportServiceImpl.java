package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.SportMapper;
import com.brilliantsofts.EliteUniversity.dto.request.SportRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SportResponse;
import com.brilliantsofts.EliteUniversity.entity.Sport;
import com.brilliantsofts.EliteUniversity.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SportServiceImpl implements SportService {

    private final SportRepository repository;

    @Override
    public SportResponse create(SportRequest request) {
        Sport entity = SportMapper.toEntity(request);
        return SportMapper.toResponse(repository.save(entity));
    }

    @Override
    public SportResponse update(Long id, SportRequest request) {
        Sport entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sport not found with id: " + id));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setCoachName(request.getCoachName());
        entity.setMaxParticipants(request.getMaxParticipants());
        entity.setActive(request.isActive());
        return SportMapper.toResponse(repository.save(entity));
    }

    @Override
    public SportResponse getById(Long id) {
        Sport entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sport not found with id: " + id));
        return SportMapper.toResponse(entity);
    }

    @Override
    public Page<SportResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(SportMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Sport not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
