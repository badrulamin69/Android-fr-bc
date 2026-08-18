package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.DocumentVerificationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.DocumentVerificationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DocumentVerificationResponse;
import com.brilliantsofts.EliteUniversity.entity.DocumentVerification;
import com.brilliantsofts.EliteUniversity.repository.DocumentVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentVerificationServiceImpl implements DocumentVerificationService {

    private final DocumentVerificationRepository repository;

    @Override
    public DocumentVerificationResponse create(DocumentVerificationRequest request) {
        DocumentVerification entity = DocumentVerificationMapper.toEntity(request);
        return DocumentVerificationMapper.toResponse(repository.save(entity));
    }

    @Override
    public DocumentVerificationResponse update(Long id, DocumentVerificationRequest request) {
        DocumentVerification entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DocumentVerification not found with id: " + id));
        entity.setAdmissionCandidateId(request.getAdmissionCandidateId());
        entity.setDocumentType(request.getDocumentType());
        entity.setDocumentNumber(request.getDocumentNumber());
        entity.setVerified(request.isVerified());
        entity.setVerifiedBy(request.getVerifiedBy());
        entity.setRemarks(request.getRemarks());
        return DocumentVerificationMapper.toResponse(repository.save(entity));
    }

    @Override
    public DocumentVerificationResponse getById(Long id) {
        DocumentVerification entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DocumentVerification not found with id: " + id));
        return DocumentVerificationMapper.toResponse(entity);
    }

    @Override
    public Page<DocumentVerificationResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(DocumentVerificationMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("DocumentVerification not found with id: " + id);
        }
        repository.deleteById(id);
    }
}