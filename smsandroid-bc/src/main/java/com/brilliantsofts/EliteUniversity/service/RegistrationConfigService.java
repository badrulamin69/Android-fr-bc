package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.RegistrationConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RegistrationConfigResponse;
import java.util.List;

public interface RegistrationConfigService {
    RegistrationConfigResponse create(RegistrationConfigRequest request);
    RegistrationConfigResponse update(Long id, RegistrationConfigRequest request);
    RegistrationConfigResponse getById(Long id);
    List<RegistrationConfigResponse> getAll();
    List<RegistrationConfigResponse> getActive();
    RegistrationConfigResponse getBySemester(Long semesterId);
    RegistrationConfigResponse closeRegistration(Long id);
    void delete(Long id);
}
