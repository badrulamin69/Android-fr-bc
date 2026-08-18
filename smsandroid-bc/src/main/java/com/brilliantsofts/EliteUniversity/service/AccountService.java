package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AccountRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AccountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    AccountResponse create(AccountRequest request);
    AccountResponse update(Long id, AccountRequest request);
    AccountResponse getById(Long id);
    Page<AccountResponse> getAll(Pageable pageable);
    void delete(Long id);
}
