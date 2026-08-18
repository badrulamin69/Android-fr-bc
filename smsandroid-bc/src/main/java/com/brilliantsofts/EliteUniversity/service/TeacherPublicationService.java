package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.TeacherPublicationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TeacherPublicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherPublicationService {
    TeacherPublicationResponse create(TeacherPublicationRequest request);
    TeacherPublicationResponse update(Long id, TeacherPublicationRequest request);
    TeacherPublicationResponse getById(Long id);
    Page<TeacherPublicationResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
