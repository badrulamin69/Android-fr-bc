package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.ExaminationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExaminationResponse;
import com.brilliantsofts.EliteUniversity.entity.Examination;
import com.brilliantsofts.EliteUniversity.dto.mapper.ExaminationMapper;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.ExaminationRepository;
import com.brilliantsofts.EliteUniversity.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    private ExaminationRepository repository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public ExaminationResponse create(ExaminationRequest request) {
        Examination entity = ExaminationMapper.toEntity(request);
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        return ExaminationMapper.toResponse(repository.save(entity));
    }
    @Override
    public ExaminationResponse update(Long id, ExaminationRequest request) {
        Examination entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Examination not found"));
        entity.setExaminationName(request.getExaminationName());
        entity.setSemester(request.getSemester());
        entity.setTotalMarks(request.getTotalMarks());
        entity.setPassMarks(request.getPassMarks());
        entity.setExaminationDate(request.getExaminationDate());
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        return ExaminationMapper.toResponse(repository.save(entity));
    }
    @Override
    public ExaminationResponse getById(Long id) {
        return ExaminationMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Examination not found")));
    }
    @Override
    public Page<ExaminationResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(ExaminationMapper::toResponse);
    }
    @Override
    public List<ExaminationResponse> getByCourse(Long courseId) {
        return repository.findByCourseId(courseId).stream().map(ExaminationMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<ExaminationResponse> getBySemester(String semester) {
        return repository.findBySemester(semester).stream().map(ExaminationMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
