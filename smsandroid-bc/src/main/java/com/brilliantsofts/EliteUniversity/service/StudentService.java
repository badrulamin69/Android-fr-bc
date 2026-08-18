package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.StudentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    StudentResponse create(StudentRequest request);
    StudentResponse update(Long id, StudentRequest request);
    StudentResponse getById(Long id);
    StudentResponse getByStudentId(String studentId);
    StudentResponse getByUserId(Long userId);
    StudentResponse getByApplicantId(Long applicantId);
    Page<StudentResponse> getAll(Pageable pageable);
    List<StudentResponse> getByProgram(Long programId);
    List<StudentResponse> getBySession(Long sessionId);
    void delete(Long id);
}
