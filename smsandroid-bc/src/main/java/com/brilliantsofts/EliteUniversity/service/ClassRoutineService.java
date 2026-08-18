package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ClassRoutineRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ClassRoutineResponse;
import com.brilliantsofts.EliteUniversity.dto.response.ConflictCheckResponse;

import java.util.List;

public interface ClassRoutineService {
    ClassRoutineResponse create(ClassRoutineRequest request);
    ClassRoutineResponse update(Long id, ClassRoutineRequest request);
    ClassRoutineResponse getById(Long id);
    List<ClassRoutineResponse> getAll(Long semesterId, Long sectionId, String dayOfWeek);
    void delete(Long id);
    List<ClassRoutineResponse> getBySemesterAndSection(Long semesterId, Long sectionId);
    ConflictCheckResponse checkConflicts(ClassRoutineRequest request);
}
