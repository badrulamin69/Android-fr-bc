package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.PreAdmissionRegistration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PreAdmissionRegistrationRepository extends JpaRepository<PreAdmissionRegistration, Long> {

    PreAdmissionRegistration findByRegistrationNumber(String registrationNumber);

    PreAdmissionRegistration findByTrackingNumber(String trackingNumber);

    boolean existsByEmail(String email);

    Optional<PreAdmissionRegistration> findByEmail(String email);

    java.util.List<PreAdmissionRegistration> findByCircularId(Long circularId);

    java.util.List<PreAdmissionRegistration> findByCircularIdAndStatus(Long circularId, String status);

    @Query("SELECT p FROM PreAdmissionRegistration p WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.registrationNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.phone) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<PreAdmissionRegistration> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
