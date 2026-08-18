package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.LiveClassRequest;
import com.brilliantsofts.EliteUniversity.dto.response.LiveClassResponse;

import java.util.List;

public interface LiveClassService {
    LiveClassResponse create(LiveClassRequest request);
    LiveClassResponse update(Long id, LiveClassRequest request);
    LiveClassResponse getById(Long id);
    List<LiveClassResponse> getAll();
    List<LiveClassResponse> getByModule(Long moduleId);
    List<LiveClassResponse> getByTeacher(Long teacherId);
    List<LiveClassResponse> getUpcoming();
    void delete(Long id);
}
