package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.CreditRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CreditRuleRepository extends JpaRepository<CreditRule, Long> {

    @Query("SELECT c FROM CreditRule c WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(c.description) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<CreditRule> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
