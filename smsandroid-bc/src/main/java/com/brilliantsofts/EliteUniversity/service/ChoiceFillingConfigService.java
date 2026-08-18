package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ChoiceFillingConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ChoiceFillingConfigResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ChoiceFillingConfigService {
    ChoiceFillingConfigResponse create(ChoiceFillingConfigRequest request);
    ChoiceFillingConfigResponse update(Long id, ChoiceFillingConfigRequest request);
    ChoiceFillingConfigResponse getById(Long id);
    Page<ChoiceFillingConfigResponse> getAll(Pageable pageable, String search, String status);
    ChoiceFillingConfigResponse activate(Long id);
    ChoiceFillingConfigResponse close(Long id);
    ChoiceFillingConfigResponse getActiveConfig();
    Map<String, Object> getStats();
    void delete(Long id);
}
