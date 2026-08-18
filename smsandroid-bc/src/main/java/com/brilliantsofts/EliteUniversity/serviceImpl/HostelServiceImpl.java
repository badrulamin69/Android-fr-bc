package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.HostelMapper;
import com.brilliantsofts.EliteUniversity.dto.request.HostelRequest;
import com.brilliantsofts.EliteUniversity.dto.response.HostelResponse;
import com.brilliantsofts.EliteUniversity.entity.Hostel;
import com.brilliantsofts.EliteUniversity.repository.HostelRepository;
import com.brilliantsofts.EliteUniversity.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HostelServiceImpl implements HostelService {

    @Autowired
    private HostelRepository repository;

    @Override
    public HostelResponse create(HostelRequest request) {
        Hostel entity = HostelMapper.toEntity(request);
        entity.setUniqueCode("HOS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return HostelMapper.toResponse(repository.save(entity));
    }

    @Override
    public HostelResponse update(Long id, HostelRequest request) {
        Hostel entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Hostel not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setType(request.getType());
        entity.setAddress(request.getAddress());
        entity.setWardensName(request.getWardensName());
        entity.setWardensPhone(request.getWardensPhone());
        entity.setTotalRooms(request.getTotalRooms());
        return HostelMapper.toResponse(repository.save(entity));
    }

    @Override
    public HostelResponse getById(Long id) {
        return HostelMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Hostel not found")));
    }

    @Override
    public Page<HostelResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(HostelMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
