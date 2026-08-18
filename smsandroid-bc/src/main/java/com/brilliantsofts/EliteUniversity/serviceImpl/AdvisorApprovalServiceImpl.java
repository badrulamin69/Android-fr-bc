package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AdvisorApprovalMapper;
import com.brilliantsofts.EliteUniversity.dto.mapper.CourseRegistrationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AdvisorApprovalRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdvisorApprovalResponse;
import com.brilliantsofts.EliteUniversity.entity.AdvisorApproval;
import com.brilliantsofts.EliteUniversity.entity.CourseRegistration;
import com.brilliantsofts.EliteUniversity.repository.AdvisorApprovalRepository;
import com.brilliantsofts.EliteUniversity.repository.CourseRegistrationRepository;
import com.brilliantsofts.EliteUniversity.service.AdvisorApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvisorApprovalServiceImpl implements AdvisorApprovalService {
    @Autowired
    private AdvisorApprovalRepository advisorApprovalRepository;
    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    @Override
    public List<AdvisorApprovalResponse> getPendingApprovals(Long semesterId) {
        List<CourseRegistration> pending = courseRegistrationRepository.findAll().stream()
                .filter(r -> r.getSemester() != null && r.getSemester().getId().equals(semesterId))
                .filter(r -> "PENDING".equalsIgnoreCase(r.getStatus()))
                .collect(Collectors.toList());
        List<AdvisorApprovalResponse> responses = new ArrayList<>();
        for (CourseRegistration reg : pending) {
            AdvisorApproval approval = new AdvisorApproval();
            approval.setRegistration(reg);
            responses.add(AdvisorApprovalMapper.toResponse(approval));
        }
        return responses;
    }

    @Override
    public AdvisorApprovalResponse processApproval(AdvisorApprovalRequest request) {
        AdvisorApproval entity = new AdvisorApproval();
        entity.setAction(request.getAction());
        entity.setComments(request.getComments());
        if (request.getRegistrationIds() != null && !request.getRegistrationIds().isEmpty()) {
            CourseRegistration reg = courseRegistrationRepository.findById(request.getRegistrationIds().get(0)).orElse(null);
            entity.setRegistration(reg);
            if (reg != null) {
                reg.setStatus("APPROVED".equalsIgnoreCase(request.getAction()) ? "APPROVED" : "REJECTED");
                courseRegistrationRepository.save(reg);
            }
        }
        return AdvisorApprovalMapper.toResponse(advisorApprovalRepository.save(entity));
    }

    @Override
    public List<AdvisorApprovalResponse> processBulkApproval(List<Long> studentIds, Long semesterId, String action, String comments) {
        List<AdvisorApprovalResponse> responses = new ArrayList<>();
        for (Long studentId : studentIds) {
            List<CourseRegistration> registrations = courseRegistrationRepository.findAll().stream()
                    .filter(r -> r.getStudent() != null && r.getStudent().getId().equals(studentId))
                    .filter(r -> r.getSemester() != null && r.getSemester().getId().equals(semesterId))
                    .collect(Collectors.toList());
            for (CourseRegistration reg : registrations) {
                AdvisorApproval entity = new AdvisorApproval();
                entity.setRegistration(reg);
                entity.setAction(action);
                entity.setComments(comments);
                reg.setStatus("APPROVED".equalsIgnoreCase(action) ? "APPROVED" : "REJECTED");
                courseRegistrationRepository.save(reg);
                responses.add(AdvisorApprovalMapper.toResponse(advisorApprovalRepository.save(entity)));
            }
        }
        return responses;
    }
}
