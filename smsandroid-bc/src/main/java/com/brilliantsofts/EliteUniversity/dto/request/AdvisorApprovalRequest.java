package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class AdvisorApprovalRequest {
    private List<Long> registrationIds;
    private String action;
    private String comments;
}
