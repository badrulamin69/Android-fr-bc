package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.TransportRoute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransportRouteRepository extends JpaRepository<TransportRoute, Long> {

    @Query("SELECT tr FROM TransportRoute tr WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(tr.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(tr.routeCode) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<TransportRoute> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
