package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AdmitCardMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AdmitCardRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmitCardResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmitCard;
import com.brilliantsofts.EliteUniversity.repository.AdmitCardRepository;
import com.brilliantsofts.EliteUniversity.service.AdmitCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdmitCardServiceImpl implements AdmitCardService {

    private final AdmitCardRepository repository;

    @Override
    @Transactional
    public AdmitCardResponse create(AdmitCardRequest request) {
        AdmitCard entity = AdmitCardMapper.toEntity(request);
        return AdmitCardMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public AdmitCardResponse update(Long id, AdmitCardRequest request) {
        AdmitCard entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admit card not found"));
        entity.setRegistrationId(request.getRegistrationId());
        entity.setTestId(request.getTestId());
        entity.setAdmitCardNumber(request.getAdmitCardNumber());
        entity.setRollNumber(request.getRollNumber());
        entity.setSeatNumber(request.getSeatNumber());
        entity.setCenterName(request.getCenterName());
        entity.setBuildingName(request.getBuildingName());
        entity.setRoomName(request.getRoomName());
        entity.setQrCode(request.getQrCode());
        entity.setIssuedAt(request.getIssuedAt());
        entity.setStatus(request.getStatus());
        return AdmitCardMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmitCardResponse getById(Long id) {
        AdmitCard entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admit card not found"));
        return AdmitCardMapper.toResponse(entity);
    }

    @Override
    public Page<AdmitCardResponse> getAll(int page, int size, String sortBy, String sortDir, String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(AdmitCardMapper::toResponse);
        }
        return repository.findAll(pageable).map(AdmitCardMapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<AdmitCardResponse> findByTestId(Long testId) {
        return repository.findByTestId(testId).stream()
                .map(AdmitCardMapper::toResponse)
                .toList();
    }

    @Override
    public List<AdmitCardResponse> findByRegistrationId(Long registrationId) {
        return repository.findByRegistrationId(registrationId).stream()
                .map(AdmitCardMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public AdmitCardResponse generate(Long testId) {
        AdmitCard entity = new AdmitCard();
        entity.setTestId(testId);
        entity.setAdmitCardNumber("AC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setRollNumber("ROLL-" + (repository.countByTestId(testId) + 1));
        entity.setSeatNumber(String.valueOf(repository.countByTestId(testId) + 1));
        entity.setIssuedAt(LocalDateTime.now());
        entity.setStatus("GENERATED");
        return AdmitCardMapper.toResponse(repository.save(entity));
    }

    @Override
    public byte[] getPdf(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admit card not found"));
        return new byte[0];
    }
}
