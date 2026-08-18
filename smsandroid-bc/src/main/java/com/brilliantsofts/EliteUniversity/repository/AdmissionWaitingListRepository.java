package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionWaitingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionWaitingListRepository extends JpaRepository<AdmissionWaitingList, Long> {
    List<AdmissionWaitingList> findByStatus(String status);
    List<AdmissionWaitingList> findByAcademicYear(String academicYear);
    List<AdmissionWaitingList> findByTestId(Long testId);
}
