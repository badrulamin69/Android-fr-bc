package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.PdfNote;

import java.util.List;

public interface PdfNoteRepository extends org.springframework.data.jpa.repository.JpaRepository<PdfNote, Long> {
    List<PdfNote> findByModuleId(Long moduleId);
}
