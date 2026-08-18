package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionTestQuestionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestQuestionResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionTestQuestion;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionTestQuestionMapper;
import com.brilliantsofts.EliteUniversity.repository.AdmissionTestQuestionRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionTestQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdmissionTestQuestionServiceImpl implements AdmissionTestQuestionService {
    @Autowired
    private AdmissionTestQuestionRepository repository;

    @Override
    public AdmissionTestQuestionResponse create(AdmissionTestQuestionRequest request) {
        AdmissionTestQuestion entity = AdmissionTestQuestionMapper.toEntity(request);
        return AdmissionTestQuestionMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionTestQuestionResponse update(Long id, AdmissionTestQuestionRequest request) {
        AdmissionTestQuestion entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionTestQuestion not found"));
        entity.setQuestionText(request.getQuestionText());
        entity.setOptionA(request.getOptionA());
        entity.setOptionB(request.getOptionB());
        entity.setOptionC(request.getOptionC());
        entity.setOptionD(request.getOptionD());
        entity.setOptionE(request.getOptionE());
        entity.setCorrectOption(request.getCorrectOption());
        entity.setMarks(request.getMarks());
        entity.setNegativeMarks(request.getNegativeMarks());
        entity.setTestId(request.getTestId());
        entity.setSubject(request.getSubject());
        entity.setDifficulty(request.getDifficulty());
        entity.setExplanation(request.getExplanation());
        entity.setQuestionType(request.getQuestionType());
        entity.setIsActive(request.getIsActive());
        return AdmissionTestQuestionMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionTestQuestionResponse getById(Long id) {
        return AdmissionTestQuestionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionTestQuestion not found")));
    }

    @Override
    public Page<AdmissionTestQuestionResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(AdmissionTestQuestionMapper::toResponse);
    }

    @Override
    public List<AdmissionTestQuestionResponse> getByTestId(Long testId) {
        return repository.findByTestId(testId).stream().map(AdmissionTestQuestionMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public long countByTestId(Long testId) {
        return repository.countByTestId(testId);
    }
}
