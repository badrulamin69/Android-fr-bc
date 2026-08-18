package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AccountRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AccountResponse;
import com.brilliantsofts.EliteUniversity.entity.Account;
import com.brilliantsofts.EliteUniversity.dto.mapper.AccountMapper;
import com.brilliantsofts.EliteUniversity.repository.AccountRepository;
import com.brilliantsofts.EliteUniversity.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository repository;

    @Override
    public AccountResponse create(AccountRequest request) {
        return AccountMapper.toResponse(repository.save(AccountMapper.toEntity(request)));
    }

    @Override
    public AccountResponse update(Long id, AccountRequest request) {
        Account entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        entity.setUniqueCode(request.getUniqueCode());
        entity.setAccountName(request.getAccountName());
        entity.setAccountNumber(request.getAccountNumber());
        entity.setAccountType(request.getAccountType());
        entity.setBalance(request.getBalance());
        entity.setDescription(request.getDescription());
        entity.setIsActive(request.getIsActive());
        return AccountMapper.toResponse(repository.save(entity));
    }

    @Override
    public AccountResponse getById(Long id) {
        return AccountMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found")));
    }

    @Override
    public Page<AccountResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(AccountMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
