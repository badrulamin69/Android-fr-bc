package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ClassroomRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ClassroomResponse;

import java.util.List;

public interface ClassroomService {
    ClassroomResponse create(ClassroomRequest request);
    ClassroomResponse update(Long id, ClassroomRequest request);
    ClassroomResponse getById(Long id);
    List<ClassroomResponse> getAll();
    void delete(Long id);
}
