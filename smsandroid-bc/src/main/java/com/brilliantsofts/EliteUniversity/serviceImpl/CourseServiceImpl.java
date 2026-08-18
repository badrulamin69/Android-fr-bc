package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.CourseRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseResponse;
import com.brilliantsofts.EliteUniversity.entity.Course;
import com.brilliantsofts.EliteUniversity.dto.mapper.CourseMapper;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.DepartmentRepository;
import com.brilliantsofts.EliteUniversity.repository.ProgramRepository;
import com.brilliantsofts.EliteUniversity.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository repository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ProgramRepository programRepository;

    @Override
    public CourseResponse create(CourseRequest request) {
        Course entity = CourseMapper.toEntity(request);
        if (request.getDepartmentId() != null) entity.setDepartment(departmentRepository.findById(request.getDepartmentId()).orElse(null));
        if (request.getProgramId() != null) entity.setProgram(programRepository.findById(request.getProgramId()).orElse(null));
        return CourseMapper.toResponse(repository.save(entity));
    }
    @Override
    public CourseResponse update(Long id, CourseRequest request) {
        Course entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        entity.setCourseName(request.getCourseName());
        entity.setCourseCode(request.getCourseCode());
        entity.setCredit(request.getCredit());
        entity.setDescription(request.getDescription());
        if (request.getDepartmentId() != null) entity.setDepartment(departmentRepository.findById(request.getDepartmentId()).orElse(null));
        if (request.getProgramId() != null) entity.setProgram(programRepository.findById(request.getProgramId()).orElse(null));
        return CourseMapper.toResponse(repository.save(entity));
    }
    @Override
    public CourseResponse getById(Long id) {
        return CourseMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Course not found")));
    }
    @Override
    public CourseResponse getByCode(String code) {
        return CourseMapper.toResponse(repository.findByCourseCode(code));
    }
    @Override
    public List<CourseResponse> getAll() {
        return repository.findAll().stream().map(CourseMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public org.springframework.data.domain.Page<CourseResponse> getAll(int page, int size, String sortBy, String sortDir, String search) {
        org.springframework.data.domain.Sort sort = sortDir.equalsIgnoreCase("desc") ?
                org.springframework.data.domain.Sort.by(sortBy).descending() :
                org.springframework.data.domain.Sort.by(sortBy).ascending();
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, sort);
        return repository.searchCourses(search, pageable).map(CourseMapper::toResponse);
    }

    @Override
    public List<CourseResponse> getByDepartment(Long departmentId) {
        return repository.findByDepartmentId(departmentId).stream().map(CourseMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<CourseResponse> getByProgram(Long programId) {
        return repository.findByProgramId(programId).stream().map(CourseMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
