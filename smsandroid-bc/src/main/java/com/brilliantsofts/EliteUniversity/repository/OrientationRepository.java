package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Orientation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrientationRepository extends JpaRepository<Orientation, Long> {

    @Query("SELECT o FROM Orientation o WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(o.title) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
            "(:status IS NULL OR :status = '' OR LOWER(o.status) = LOWER(:status))")
    Page<Orientation> findAllWithSearchAndStatus(
            @Param("search") String search,
            @Param("status") String status,
            Pageable pageable);

    @Query("SELECT o FROM Orientation o WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(o.title) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Orientation> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
