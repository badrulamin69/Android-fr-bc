package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM Teacher t WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(t.teacherCode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.designation) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Teacher> findAllWithSearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT t FROM Teacher t WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(t.teacherCode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.designation) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
            "(:departmentId IS NULL OR t.departmentId = :departmentId) AND " +
            "(:facultyId IS NULL OR t.facultyId = :facultyId) AND " +
            "(:designation IS NULL OR t.designation = :designation) AND " +
            "(:status IS NULL OR t.status = :status)")
    Page<Teacher> findAllFiltered(@Param("search") String search,
                                  @Param("departmentId") Long departmentId,
                                  @Param("facultyId") Long facultyId,
                                  @Param("designation") String designation,
                                  @Param("status") String status,
                                  Pageable pageable);
}
