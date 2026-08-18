package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

    @Query("SELECT b FROM BookCategory b WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(b.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(b.code) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<BookCategory> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
