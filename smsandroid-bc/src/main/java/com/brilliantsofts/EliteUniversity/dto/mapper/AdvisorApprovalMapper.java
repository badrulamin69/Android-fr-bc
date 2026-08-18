package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.response.AdvisorApprovalResponse;
import com.brilliantsofts.EliteUniversity.entity.AdvisorApproval;

public class AdvisorApprovalMapper {
    public static AdvisorApprovalResponse toResponse(AdvisorApproval entity) {
        AdvisorApprovalResponse response = new AdvisorApprovalResponse();
        response.setId(entity.getId());
        response.setAction(entity.getAction());
        response.setComments(entity.getComments());
        response.setCreatedAt(entity.getCreatedAt());
        if (entity.getRegistration() != null) {
            response.setRegistrationId(entity.getRegistration().getId());
            if (entity.getRegistration().getStudent() != null) {
                response.setStudentId(entity.getRegistration().getStudent().getId());
                response.setStudentName(entity.getRegistration().getStudent().getFullName());
                response.setStudentCode(entity.getRegistration().getStudent().getStudentId());
            }
            if (entity.getRegistration().getCourse() != null) {
                response.setCourseId(entity.getRegistration().getCourse().getId());
                response.setCourseName(entity.getRegistration().getCourse().getCourseName());
            }
            if (entity.getRegistration().getSemester() != null) {
                response.setSemesterId(entity.getRegistration().getSemester().getId());
                response.setSemesterName(entity.getRegistration().getSemester().getName());
            }
        }
        if (entity.getAdvisor() != null) {
            response.setAdvisorId(entity.getAdvisor().getId());
            response.setAdvisorName(entity.getAdvisor().getFullName());
        }
        return response;
    }
}
