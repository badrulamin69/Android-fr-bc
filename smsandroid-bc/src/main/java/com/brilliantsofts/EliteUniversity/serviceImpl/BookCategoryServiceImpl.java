package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.BookCategoryMapper;
import com.brilliantsofts.EliteUniversity.dto.request.BookCategoryRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BookCategoryResponse;
import com.brilliantsofts.EliteUniversity.entity.BookCategory;
import com.brilliantsofts.EliteUniversity.repository.BookCategoryRepository;
import com.brilliantsofts.EliteUniversity.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    private BookCategoryRepository repository;

    @Override
    public BookCategoryResponse create(BookCategoryRequest request) {
        BookCategory entity = BookCategoryMapper.toEntity(request);
        entity.setUniqueCode("BC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return BookCategoryMapper.toResponse(repository.save(entity));
    }

    @Override
    public BookCategoryResponse update(Long id, BookCategoryRequest request) {
        BookCategory entity = repository.findById(id).orElseThrow(() -> new RuntimeException("BookCategory not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        return BookCategoryMapper.toResponse(repository.save(entity));
    }

    @Override
    public BookCategoryResponse getById(Long id) {
        return BookCategoryMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("BookCategory not found")));
    }

    @Override
    public Page<BookCategoryResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(BookCategoryMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
