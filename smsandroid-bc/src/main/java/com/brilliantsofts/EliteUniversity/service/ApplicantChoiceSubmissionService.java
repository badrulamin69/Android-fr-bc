package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ApplicantChoiceSubmissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantChoiceResponse;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantChoiceSubmissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ApplicantChoiceSubmissionService {
    ApplicantChoiceSubmissionResponse startSubmission(Long configId, Long registrationId, String applicantName, Integer meritRank, Double meritScore);
    ApplicantChoiceSubmissionResponse getMySubmission(Long registrationId, Long configId);
    List<ApplicantChoiceResponse> getMyChoices(Long submissionId);
    ApplicantChoiceResponse addChoice(Long submissionId, Long programId, String programName, Long facultyId, String facultyName, Long departmentId, String departmentName, String shift);
    void removeChoice(Long choiceId);
    ApplicantChoiceResponse moveChoice(Long choiceId, String direction);
    ApplicantChoiceSubmissionResponse submit(Long submissionId);
    Page<ApplicantChoiceSubmissionResponse> getAdminSubmissions(Pageable pageable, String search, String status, Long configId);
    ApplicantChoiceSubmissionResponse getAdminSubmissionById(Long id);
    List<ApplicantChoiceResponse> getAdminSubmissionChoices(String submissionId);
    ApplicantChoiceSubmissionResponse lockSubmission(Long id);
    ApplicantChoiceSubmissionResponse reopenSubmission(Long id);
    Map<String, Object> getAdminStats(Long configId);
}
