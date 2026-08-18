package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.PdfNoteRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PdfNoteResponse;

import java.util.List;

public interface PdfNoteService {
    PdfNoteResponse create(PdfNoteRequest request);
    PdfNoteResponse update(Long id, PdfNoteRequest request);
    PdfNoteResponse getById(Long id);
    List<PdfNoteResponse> getAll();
    List<PdfNoteResponse> getByModule(Long moduleId);
    void delete(Long id);
}
