package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.ExaminationResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExaminationResultResponse;
import com.brilliantsofts.EliteUniversity.entity.ExaminationResult;
import com.brilliantsofts.EliteUniversity.dto.mapper.ExaminationResultMapper;
import com.brilliantsofts.EliteUniversity.repository.ExaminationRepository;
import com.brilliantsofts.EliteUniversity.repository.ExaminationResultRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.ExaminationResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExaminationResultServiceImpl implements ExaminationResultService {
    @Autowired
    private ExaminationResultRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ExaminationRepository examinationRepository;

    @Override
    public ExaminationResultResponse create(ExaminationResultRequest request) {
        ExaminationResult entity = ExaminationResultMapper.toEntity(request);
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        if (request.getExaminationId() != null) entity.setExamination(examinationRepository.findById(request.getExaminationId()).orElse(null));
        return ExaminationResultMapper.toResponse(repository.save(entity));
    }
    @Override
    public ExaminationResultResponse update(Long id, ExaminationResultRequest request) {
        ExaminationResult entity = repository.findById(id).orElseThrow(() -> new RuntimeException("ExaminationResult not found"));
        entity.setMarks(request.getMarks());
        entity.setGradePoint(request.getGradePoint());
        entity.setGrade(request.getGrade());
        entity.setCredit(request.getCredit());
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        if (request.getExaminationId() != null) entity.setExamination(examinationRepository.findById(request.getExaminationId()).orElse(null));
        return ExaminationResultMapper.toResponse(repository.save(entity));
    }
    @Override
    public ExaminationResultResponse getById(Long id) {
        return ExaminationResultMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("ExaminationResult not found")));
    }
    @Override
    public List<ExaminationResultResponse> getAll() {
        return repository.findAll().stream().map(ExaminationResultMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<ExaminationResultResponse> getByStudent(Long studentId) {
        return repository.findByStudentId(studentId).stream().map(ExaminationResultMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<ExaminationResultResponse> getByExamination(Long examinationId) {
        return repository.findByExaminationId(examinationId).stream().map(ExaminationResultMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
