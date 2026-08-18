package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.BookReturnMapper;
import com.brilliantsofts.EliteUniversity.dto.request.BookReturnRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BookReturnResponse;
import com.brilliantsofts.EliteUniversity.entity.BookIssue;
import com.brilliantsofts.EliteUniversity.entity.BookReturn;
import com.brilliantsofts.EliteUniversity.repository.BookIssueRepository;
import com.brilliantsofts.EliteUniversity.repository.BookReturnRepository;
import com.brilliantsofts.EliteUniversity.service.BookReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookReturnServiceImpl implements BookReturnService {

    @Autowired
    private BookReturnRepository repository;

    @Autowired
    private BookIssueRepository bookIssueRepository;

    @Override
    public BookReturnResponse create(BookReturnRequest request) {
        BookReturn entity = BookReturnMapper.toEntity(request);
        entity.setUniqueCode("BR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        BookIssue bookIssue = bookIssueRepository.findById(request.getBookIssueId())
                .orElseThrow(() -> new RuntimeException("BookIssue not found"));
        entity.setBookIssue(bookIssue);
        return BookReturnMapper.toResponse(repository.save(entity));
    }

    @Override
    public BookReturnResponse update(Long id, BookReturnRequest request) {
        BookReturn entity = repository.findById(id).orElseThrow(() -> new RuntimeException("BookReturn not found"));
        entity.setReturnDate(request.getReturnDate());
        entity.setFineAmount(request.getFineAmount());
        entity.setFinePaid(Boolean.TRUE.equals(request.getFinePaid()));
        entity.setConditionAtReturn(request.getConditionAtReturn());
        entity.setRemarks(request.getRemarks());
        if (request.getBookIssueId() != null) {
            BookIssue bookIssue = bookIssueRepository.findById(request.getBookIssueId())
                    .orElseThrow(() -> new RuntimeException("BookIssue not found"));
            entity.setBookIssue(bookIssue);
        }
        return BookReturnMapper.toResponse(repository.save(entity));
    }

    @Override
    public BookReturnResponse getById(Long id) {
        return BookReturnMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("BookReturn not found")));
    }

    @Override
    public Page<BookReturnResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(BookReturnMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
