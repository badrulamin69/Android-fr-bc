package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.BookIssueRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BookIssueResponse;
import com.brilliantsofts.EliteUniversity.entity.BookIssue;
import com.brilliantsofts.EliteUniversity.enums.BookIssueStatus;
import com.brilliantsofts.EliteUniversity.dto.mapper.BookIssueMapper;
import com.brilliantsofts.EliteUniversity.repository.BookIssueRepository;
import com.brilliantsofts.EliteUniversity.repository.BookRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.BookIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookIssueServiceImpl implements BookIssueService {
    @Autowired
    private BookIssueRepository repository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public BookIssueResponse issueBook(BookIssueRequest request) {
        BookIssue entity = BookIssueMapper.toEntity(request);
        entity.setStatus(BookIssueStatus.ISSUED);
        if (request.getBookId() != null) entity.setBook(bookRepository.findById(request.getBookId()).orElse(null));
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        return BookIssueMapper.toResponse(repository.save(entity));
    }
    @Override
    public BookIssueResponse returnBook(Long issueId) {
        BookIssue entity = repository.findById(issueId).orElseThrow(() -> new RuntimeException("BookIssue not found"));
        entity.setReturnDate(LocalDate.now());
        entity.setStatus(BookIssueStatus.RETURNED);
        return BookIssueMapper.toResponse(repository.save(entity));
    }
    @Override
    public BookIssueResponse getById(Long id) {
        return BookIssueMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("BookIssue not found")));
    }
    @Override
    public Page<BookIssueResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(BookIssueMapper::toResponse);
    }
    @Override
    public List<BookIssueResponse> getByStudent(Long studentId) {
        return repository.findByStudentId(studentId).stream().map(BookIssueMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<BookIssueResponse> getByBook(Long bookId) {
        return repository.findByBookId(bookId).stream().map(BookIssueMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<BookIssueResponse> getByStatus(BookIssueStatus status) {
        return repository.findByStatus(status).stream().map(BookIssueMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
