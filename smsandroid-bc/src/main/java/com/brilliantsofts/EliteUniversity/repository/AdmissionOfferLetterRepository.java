package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionOfferLetter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdmissionOfferLetterRepository extends JpaRepository<AdmissionOfferLetter, Long> {

    List<AdmissionOfferLetter> findByApplicationId(Long applicationId);

    AdmissionOfferLetter findByLetterNumber(String letterNumber);

    @Query("SELECT a FROM AdmissionOfferLetter a WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(a.letterNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.uniqueCode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<AdmissionOfferLetter> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
