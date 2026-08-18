package com.brilliantsofts.EliteUniversity.service;

import java.util.Map;

public interface ApplicantPortalService {
    Object getMyRegistration(Long userId);
    Object getMyTest(Long userId);
    Object getTestQuestions(Long testId);
    Object startTest(Long userId, Long testId);
    Object submitTest(Long userId, Long attemptId, Map<String, String> answers);
    Object getMyResults(Long userId);
    Object getMyAllocation(Long userId);
    Object confirmAllocation(Long userId, Long allocationId);
    Object declineAllocation(Long userId, Long allocationId);
    Object enrollSelf(Long userId);
    Object getMyEnrollment(Long userId);
    Object admitAsStudent(Long userId);
    Object enrollWithChoice(Long userId, Long facultyId, Long departmentId, Long programId);
    byte[] getAdmitCardPdf(Long userId);
    String getAdmitCardHtml(Long userId);
    Object getMyMerit(Long userId);
    Object getMyWaitingPosition(Long userId);
}
