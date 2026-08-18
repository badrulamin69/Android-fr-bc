package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.CreditRuleMapper;
import com.brilliantsofts.EliteUniversity.dto.request.CreditRuleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CreditRuleResponse;
import com.brilliantsofts.EliteUniversity.entity.CreditRule;
import com.brilliantsofts.EliteUniversity.repository.CreditRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditRuleServiceImpl implements CreditRuleService {

    private final CreditRuleRepository repository;

    @Override
    public CreditRuleResponse create(CreditRuleRequest request) {
        CreditRule entity = CreditRuleMapper.toEntity(request);
        return CreditRuleMapper.toResponse(repository.save(entity));
    }

    @Override
    public CreditRuleResponse update(Long id, CreditRuleRequest request) {
        CreditRule entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CreditRule not found with id: " + id));
        entity.setProgramId(request.getProgramId());
        entity.setMinCreditsPerSemester(request.getMinCreditsPerSemester());
        entity.setMaxCreditsPerSemester(request.getMaxCreditsPerSemester());
        entity.setTotalRequiredCredits(request.getTotalRequiredCredits());
        entity.setMaxTransferCredits(request.getMaxTransferCredits());
        entity.setMaxElectiveCredits(request.getMaxElectiveCredits());
        entity.setDescription(request.getDescription());
        return CreditRuleMapper.toResponse(repository.save(entity));
    }

    @Override
    public CreditRuleResponse getById(Long id) {
        CreditRule entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CreditRule not found with id: " + id));
        return CreditRuleMapper.toResponse(entity);
    }

    @Override
    public Page<CreditRuleResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(CreditRuleMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("CreditRule not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
