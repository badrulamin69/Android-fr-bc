package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.StudentDocumentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentDocumentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface StudentDocumentService {
    StudentDocumentResponse create(StudentDocumentRequest request);
    StudentDocumentResponse update(Long id, StudentDocumentRequest request);
    StudentDocumentResponse getById(Long id);
    Page<StudentDocumentResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
