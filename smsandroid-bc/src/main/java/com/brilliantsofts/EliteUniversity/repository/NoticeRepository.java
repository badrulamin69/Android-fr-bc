package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Notice;
import com.brilliantsofts.EliteUniversity.enums.NoticeAudience;

import java.util.List;

public interface NoticeRepository extends org.springframework.data.jpa.repository.JpaRepository<Notice, Long> {
    List<Notice> findByPublishedTrue();
    List<Notice> findByAudience(NoticeAudience audience);
    List<Notice> findByFacultyId(Long facultyId);
    List<Notice> findByDepartmentId(Long departmentId);
}
