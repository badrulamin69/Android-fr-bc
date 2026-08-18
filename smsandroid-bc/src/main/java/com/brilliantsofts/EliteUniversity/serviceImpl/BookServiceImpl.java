package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.BookRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BookResponse;
import com.brilliantsofts.EliteUniversity.entity.Book;
import com.brilliantsofts.EliteUniversity.dto.mapper.BookMapper;
import com.brilliantsofts.EliteUniversity.repository.BookRepository;
import com.brilliantsofts.EliteUniversity.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository repository;

    @Override
    public BookResponse create(BookRequest request) {
        return BookMapper.toResponse(repository.save(BookMapper.toEntity(request)));
    }
    @Override
    public BookResponse update(Long id, BookRequest request) {
        Book entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        entity.setTitle(request.getTitle());
        entity.setIsbn(request.getIsbn());
        entity.setAuthor(request.getAuthor());
        entity.setQuantity(request.getQuantity());
        entity.setAvailableQuantity(request.getAvailableQuantity());
        return BookMapper.toResponse(repository.save(entity));
    }
    @Override
    public BookResponse getById(Long id) {
        return BookMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found")));
    }
    @Override
    public BookResponse getByIsbn(String isbn) {
        return BookMapper.toResponse(repository.findByIsbn(isbn));
    }
    @Override
    public Page<BookResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(BookMapper::toResponse);
    }
    @Override
    public List<BookResponse> searchByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title).stream().map(BookMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
