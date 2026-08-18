package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.NoticeRequest;
import com.brilliantsofts.EliteUniversity.dto.response.NoticeResponse;
import com.brilliantsofts.EliteUniversity.enums.NoticeAudience;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
    NoticeResponse create(NoticeRequest request);
    NoticeResponse update(Long id, NoticeRequest request);
    NoticeResponse getById(Long id);
    Page<NoticeResponse> getAll(Pageable pageable);
    List<NoticeResponse> getPublished();
    List<NoticeResponse> getByAudience(NoticeAudience audience);
    List<NoticeResponse> getByFaculty(Long facultyId);
    List<NoticeResponse> getByDepartment(Long departmentId);
    void delete(Long id);
}
