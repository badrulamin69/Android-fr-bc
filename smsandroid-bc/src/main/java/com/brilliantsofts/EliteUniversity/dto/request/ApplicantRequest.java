package com.brilliantsofts.EliteUniversity.dto.request;

import com.brilliantsofts.EliteUniversity.enums.ApplicationLevel;
import com.brilliantsofts.EliteUniversity.enums.ApplicationStatus;
import lombok.Data;

@Data
public class ApplicantRequest {
    private String applicationNumber;
    private String fullName;
    private String phone;
    private String address;
    private ApplicationLevel applicationLevel;
    private ApplicationStatus status;
    private Long userId;
    private Long programId;
}
