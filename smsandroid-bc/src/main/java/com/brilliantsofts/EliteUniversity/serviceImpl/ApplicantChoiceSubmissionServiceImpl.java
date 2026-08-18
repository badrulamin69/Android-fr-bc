package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.ApplicantChoiceSubmissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantChoiceResponse;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantChoiceSubmissionResponse;
import com.brilliantsofts.EliteUniversity.entity.ApplicantChoice;
import com.brilliantsofts.EliteUniversity.entity.ApplicantChoiceSubmission;
import com.brilliantsofts.EliteUniversity.dto.mapper.ApplicantChoiceMapper;
import com.brilliantsofts.EliteUniversity.dto.mapper.ApplicantChoiceSubmissionMapper;
import com.brilliantsofts.EliteUniversity.repository.ApplicantChoiceRepository;
import com.brilliantsofts.EliteUniversity.repository.ApplicantChoiceSubmissionRepository;
import com.brilliantsofts.EliteUniversity.service.ApplicantChoiceSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ApplicantChoiceSubmissionServiceImpl implements ApplicantChoiceSubmissionService {
    @Autowired
    private ApplicantChoiceSubmissionRepository submissionRepository;
    @Autowired
    private ApplicantChoiceRepository choiceRepository;

    @Override
    public ApplicantChoiceSubmissionResponse startSubmission(Long configId, Long registrationId, String applicantName, Integer meritRank, Double meritScore) {
        ApplicantChoiceSubmission existing = submissionRepository.findByRegistrationIdAndConfigId(registrationId, configId).orElse(null);
        if (existing != null) {
            return ApplicantChoiceSubmissionMapper.toResponse(existing);
        }
        ApplicantChoiceSubmission entity = new ApplicantChoiceSubmission();
        entity.setConfigId(configId);
        entity.setRegistrationId(registrationId);
        entity.setSubmissionId(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setTotalChoices(0);
        entity.setStatus("DRAFT");
        entity.setApplicantName(applicantName);
        entity.setMeritRank(meritRank);
        entity.setMeritScore(meritScore);
        return ApplicantChoiceSubmissionMapper.toResponse(submissionRepository.save(entity));
    }

    @Override
    public ApplicantChoiceSubmissionResponse getMySubmission(Long registrationId, Long configId) {
        ApplicantChoiceSubmission entity = submissionRepository.findByRegistrationIdAndConfigId(registrationId, configId).orElseThrow(() -> new RuntimeException("No submission found"));
        return ApplicantChoiceSubmissionMapper.toResponse(entity);
    }

    @Override
    public List<ApplicantChoiceResponse> getMyChoices(Long submissionId) {
        return choiceRepository.findBySubmissionIdOrderByPriorityAsc(submissionId).stream().map(ApplicantChoiceMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public ApplicantChoiceResponse addChoice(Long submissionId, Long programId, String programName, Long facultyId, String facultyName, Long departmentId, String departmentName, String shift) {
        ApplicantChoiceSubmission submission = submissionRepository.findById(submissionId).orElseThrow(() -> new RuntimeException("Submission not found"));
        if ("LOCKED".equals(submission.getStatus()) || "SUBMITTED".equals(submission.getStatus())) {
            throw new RuntimeException("Submission is locked or already submitted");
        }
        choiceRepository.findBySubmissionIdAndProgramId(submissionId, programId).ifPresent(c -> {
            throw new RuntimeException("Program already added to choices");
        });
        long currentCount = choiceRepository.countBySubmissionId(submissionId);
        ApplicantChoice entity = new ApplicantChoice();
        entity.setSubmissionId(submissionId);
        entity.setPriority((int) currentCount + 1);
        entity.setFacultyId(facultyId);
        entity.setDepartmentId(departmentId);
        entity.setProgramId(programId);
        entity.setFacultyName(facultyName);
        entity.setDepartmentName(departmentName);
        entity.setProgramName(programName);
        entity.setShift(shift);
        entity.setStatus("ACTIVE");
        choiceRepository.save(entity);
        submission.setTotalChoices((int) currentCount + 1);
        submissionRepository.save(submission);
        return ApplicantChoiceMapper.toResponse(entity);
    }

    @Override
    public void removeChoice(Long choiceId) {
        ApplicantChoice choice = choiceRepository.findById(choiceId).orElseThrow(() -> new RuntimeException("Choice not found"));
        ApplicantChoiceSubmission submission = submissionRepository.findById(choice.getSubmissionId()).orElseThrow(() -> new RuntimeException("Submission not found"));
        if ("LOCKED".equals(submission.getStatus()) || "SUBMITTED".equals(submission.getStatus())) {
            throw new RuntimeException("Submission is locked or already submitted");
        }
        choiceRepository.deleteById(choiceId);
        List<ApplicantChoice> remaining = choiceRepository.findBySubmissionIdOrderByPriorityAsc(choice.getSubmissionId());
        for (int i = 0; i < remaining.size(); i++) {
            remaining.get(i).setPriority(i + 1);
            choiceRepository.save(remaining.get(i));
        }
        submission.setTotalChoices(remaining.size());
        submissionRepository.save(submission);
    }

    @Override
    public ApplicantChoiceResponse moveChoice(Long choiceId, String direction) {
        ApplicantChoice choice = choiceRepository.findById(choiceId).orElseThrow(() -> new RuntimeException("Choice not found"));
        ApplicantChoiceSubmission submission = submissionRepository.findById(choice.getSubmissionId()).orElseThrow(() -> new RuntimeException("Submission not found"));
        if ("LOCKED".equals(submission.getStatus()) || "SUBMITTED".equals(submission.getStatus())) {
            throw new RuntimeException("Submission is locked or already submitted");
        }
        List<ApplicantChoice> choices = choiceRepository.findBySubmissionIdOrderByPriorityAsc(choice.getSubmissionId());
        int currentIndex = choices.indexOf(choice);
        if ("up".equals(direction) && currentIndex > 0) {
            ApplicantChoice swap = choices.get(currentIndex - 1);
            swap.setPriority(choice.getPriority());
            choice.setPriority(choice.getPriority() - 1);
            choiceRepository.save(choice);
            choiceRepository.save(swap);
        } else if ("down".equals(direction) && currentIndex < choices.size() - 1) {
            ApplicantChoice swap = choices.get(currentIndex + 1);
            swap.setPriority(choice.getPriority());
            choice.setPriority(choice.getPriority() + 1);
            choiceRepository.save(choice);
            choiceRepository.save(swap);
        }
        return ApplicantChoiceMapper.toResponse(choiceRepository.findById(choiceId).orElse(choice));
    }

    @Override
    public ApplicantChoiceSubmissionResponse submit(Long submissionId) {
        ApplicantChoiceSubmission entity = submissionRepository.findById(submissionId).orElseThrow(() -> new RuntimeException("Submission not found"));
        if ("LOCKED".equals(entity.getStatus()) || "SUBMITTED".equals(entity.getStatus())) {
            throw new RuntimeException("Submission is locked or already submitted");
        }
        entity.setStatus("SUBMITTED");
        entity.setSubmittedAt(LocalDateTime.now());
        return ApplicantChoiceSubmissionMapper.toResponse(submissionRepository.save(entity));
    }

    @Override
    public Page<ApplicantChoiceSubmissionResponse> getAdminSubmissions(Pageable pageable, String search, String status, Long configId) {
        return submissionRepository.findAll(pageable).map(ApplicantChoiceSubmissionMapper::toResponse);
    }

    @Override
    public ApplicantChoiceSubmissionResponse getAdminSubmissionById(Long id) {
        return ApplicantChoiceSubmissionMapper.toResponse(submissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Submission not found")));
    }

    @Override
    public List<ApplicantChoiceResponse> getAdminSubmissionChoices(String submissionId) {
        ApplicantChoiceSubmission submission = submissionRepository.findBySubmissionId(submissionId).orElseThrow(() -> new RuntimeException("Submission not found"));
        return choiceRepository.findBySubmissionIdOrderByPriorityAsc(submission.getId()).stream().map(ApplicantChoiceMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public ApplicantChoiceSubmissionResponse lockSubmission(Long id) {
        ApplicantChoiceSubmission entity = submissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Submission not found"));
        entity.setStatus("LOCKED");
        entity.setLockedAt(LocalDateTime.now());
        return ApplicantChoiceSubmissionMapper.toResponse(submissionRepository.save(entity));
    }

    @Override
    public ApplicantChoiceSubmissionResponse reopenSubmission(Long id) {
        ApplicantChoiceSubmission entity = submissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Submission not found"));
        entity.setStatus("DRAFT");
        entity.setLockedAt(null);
        entity.setSubmittedAt(null);
        return ApplicantChoiceSubmissionMapper.toResponse(submissionRepository.save(entity));
    }

    @Override
    public Map<String, Object> getAdminStats(Long configId) {
        Map<String, Object> stats = new HashMap<>();
        List<ApplicantChoiceSubmission> submissions = submissionRepository.findByConfigId(configId);
        long totalSubmissions = submissions.size();
        long draftCount = submissions.stream().filter(s -> "DRAFT".equals(s.getStatus())).count();
        long submittedCount = submissions.stream().filter(s -> "SUBMITTED".equals(s.getStatus())).count();
        long lockedCount = submissions.stream().filter(s -> "LOCKED".equals(s.getStatus())).count();
        stats.put("totalSubmissions", totalSubmissions);
        stats.put("draftCount", draftCount);
        stats.put("submittedCount", submittedCount);
        stats.put("lockedCount", lockedCount);
        return stats;
    }
}
