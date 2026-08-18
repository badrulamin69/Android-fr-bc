package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(v.vehicleNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(v.driverName) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Vehicle> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
