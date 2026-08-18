package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.TransactionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TransactionResponse;
import com.brilliantsofts.EliteUniversity.entity.Transaction;
import com.brilliantsofts.EliteUniversity.dto.mapper.TransactionMapper;
import com.brilliantsofts.EliteUniversity.repository.TransactionRepository;
import com.brilliantsofts.EliteUniversity.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository repository;

    @Override
    public TransactionResponse create(TransactionRequest request) {
        return TransactionMapper.toResponse(repository.save(TransactionMapper.toEntity(request)));
    }

    @Override
    public TransactionResponse update(Long id, TransactionRequest request) {
        Transaction entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
        entity.setUniqueCode(request.getUniqueCode());
        entity.setAccountId(request.getAccountId());
        entity.setTransactionType(request.getTransactionType());
        entity.setAmount(request.getAmount());
        entity.setDescription(request.getDescription());
        entity.setReferenceType(request.getReferenceType());
        entity.setReferenceId(request.getReferenceId());
        entity.setTransactionDate(request.getTransactionDate());
        return TransactionMapper.toResponse(repository.save(entity));
    }

    @Override
    public TransactionResponse getById(Long id) {
        return TransactionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found")));
    }

    @Override
    public Page<TransactionResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(TransactionMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
