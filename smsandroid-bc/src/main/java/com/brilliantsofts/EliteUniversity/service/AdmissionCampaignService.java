package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionCampaignRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionCampaignResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AdmissionCampaignService {
    AdmissionCampaignResponse create(AdmissionCampaignRequest request);
    AdmissionCampaignResponse update(Long id, AdmissionCampaignRequest request);
    AdmissionCampaignResponse getById(Long id);
    Page<AdmissionCampaignResponse> getAll(int page, int size, String sort, String direction, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
