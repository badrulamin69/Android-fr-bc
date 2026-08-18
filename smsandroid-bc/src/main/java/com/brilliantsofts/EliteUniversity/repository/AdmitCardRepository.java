package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmitCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdmitCardRepository extends JpaRepository<AdmitCard, Long> {

    List<AdmitCard> findByTestId(Long testId);

    List<AdmitCard> findByRegistrationId(Long registrationId);

    AdmitCard findByAdmitCardNumber(String admitCardNumber);

    @Query("SELECT a FROM AdmitCard a WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(a.admitCardNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.rollNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<AdmitCard> search(@Param("search") String search, Pageable pageable);

    long countByTestId(Long testId);
}
