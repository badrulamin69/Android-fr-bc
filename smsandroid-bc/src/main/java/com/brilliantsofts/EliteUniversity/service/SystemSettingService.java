package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.SystemSettingRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SystemSettingResponse;
import java.util.List;

public interface SystemSettingService {
    SystemSettingResponse create(SystemSettingRequest request);
    SystemSettingResponse update(Long id, SystemSettingRequest request);
    SystemSettingResponse getById(Long id);
    List<SystemSettingResponse> getAll();
    List<SystemSettingResponse> getByModule(String module);
    SystemSettingResponse getByKey(String key);
    List<SystemSettingResponse> getPublic();
    void batchUpdate(List<SystemSettingRequest> settings);
    void delete(Long id);
    void deleteByKey(String key);
}
