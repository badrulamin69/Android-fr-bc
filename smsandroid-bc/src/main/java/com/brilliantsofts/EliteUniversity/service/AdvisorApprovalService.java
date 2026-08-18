package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdvisorApprovalRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdvisorApprovalResponse;

import java.util.List;

public interface AdvisorApprovalService {
    List<AdvisorApprovalResponse> getPendingApprovals(Long semesterId);
    AdvisorApprovalResponse processApproval(AdvisorApprovalRequest request);
    List<AdvisorApprovalResponse> processBulkApproval(List<Long> studentIds, Long semesterId, String action, String comments);
}
