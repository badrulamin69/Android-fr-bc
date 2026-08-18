package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.BookReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookReturnRepository extends JpaRepository<BookReturn, Long> {

    @Query("SELECT br FROM BookReturn br WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(br.uniqueCode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(br.remarks) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<BookReturn> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
