package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.response.ResultSheetResponse;
import com.brilliantsofts.EliteUniversity.dto.response.StudentResultSummary;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResultSheetService {

    ResultSheetResponse getForStudent(Long studentId, String semester);

    Page<StudentResultSummary> getStudentSummaries(int page, int size, String search,
                                                   Long facultyId, Long departmentId, Long programId,
                                                   Long academicSessionId, String semester, String status);

    Double getCgpa(Long studentId);

    List<String> getSemesters();

    byte[] generateResultSheetPdf(Long studentId, String semester);

    String generateResultSheetHtml(Long studentId, String semester);
}
