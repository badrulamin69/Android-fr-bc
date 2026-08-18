package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.ProgramSeatConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramSeatConfigRepository extends JpaRepository<ProgramSeatConfig, Long> {

    List<ProgramSeatConfig> findByConfigId(Long configId);

    List<ProgramSeatConfig> findByProgramId(Long programId);

    @Query("SELECT p FROM ProgramSeatConfig p WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(p.shift) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<ProgramSeatConfig> search(@Param("search") String search, Pageable pageable);
}
