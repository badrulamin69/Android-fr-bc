package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.PayrollMapper;
import com.brilliantsofts.EliteUniversity.dto.request.PayrollRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PayrollResponse;
import com.brilliantsofts.EliteUniversity.entity.Payroll;
import com.brilliantsofts.EliteUniversity.repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository repository;

    @Override
    public PayrollResponse create(PayrollRequest request) {
        Payroll entity = PayrollMapper.toEntity(request);
        return PayrollMapper.toResponse(repository.save(entity));
    }

    @Override
    public PayrollResponse update(Long id, PayrollRequest request) {
        Payroll entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found with id: " + id));
        entity.setEmployeeId(request.getEmployeeId());
        entity.setPayPeriodStart(request.getPayPeriodStart());
        entity.setPayPeriodEnd(request.getPayPeriodEnd());
        entity.setBasicSalary(request.getBasicSalary());
        entity.setAllowances(request.getAllowances());
        entity.setDeductions(request.getDeductions());
        entity.setNetSalary(request.getNetSalary());
        entity.setPaymentDate(request.getPaymentDate());
        if (request.getStatus() != null) entity.setStatus(request.getStatus());
        return PayrollMapper.toResponse(repository.save(entity));
    }

    @Override
    public PayrollResponse getById(Long id) {
        Payroll entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found with id: " + id));
        return PayrollMapper.toResponse(entity);
    }

    @Override
    public Page<PayrollResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(PayrollMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Payroll not found with id: " + id);
        }
        repository.deleteById(id);
    }
}