package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.SubjectMapper;
import com.brilliantsofts.EliteUniversity.dto.request.SubjectRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SubjectResponse;
import com.brilliantsofts.EliteUniversity.entity.Course;
import com.brilliantsofts.EliteUniversity.entity.Subject;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.SubjectSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectManagementServiceImpl implements SubjectManagementService {

    private final SubjectSearchRepository repository;
    private final CourseRepository courseRepository;

    private void syncSubjectsIfEmpty() {
        if (repository.count() == 0) {
            List<Course> courses = courseRepository.findAll();
            String[][] defaultSubjects = {
                { "Data Structures & Algorithms", "CSE-101", "3" },
                { "Database Management Systems", "CSE-201", "3" },
                { "Computer Networks", "CSE-301", "3" },
                { "Operating Systems", "CSE-302", "3" },
                { "Software Engineering & Architecture", "CSE-401", "3" },
                { "Artificial Intelligence & ML", "CSE-402", "3" },
                { "Business Communication & Ethics", "BBA-101", "3" },
                { "Financial Accounting & Analytics", "BBA-201", "3" },
                { "Micro & Macro Economics", "ECO-101", "3" },
                { "Applied Physics & Circuits", "EEE-101", "3" }
            };

            for (int i = 0; i < defaultSubjects.length; i++) {
                String[] s = defaultSubjects[i];
                Subject sub = new Subject();
                sub.setName(s[0]);
                sub.setCode(s[1]);
                sub.setCreditHours(Integer.parseInt(s[2]));
                if (!courses.isEmpty()) {
                    sub.setCourse(courses.get(i % courses.size()));
                }
                repository.save(sub);
            }
        }
    }

    @Override
    public SubjectResponse create(SubjectRequest request) {
        Subject entity = SubjectMapper.toEntity(request);
        if (request.getCourseId() != null) {
            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + request.getCourseId()));
            entity.setCourse(course);
        }
        return SubjectMapper.toResponse(repository.save(entity));
    }

    @Override
    public SubjectResponse update(Long id, SubjectRequest request) {
        Subject entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setCreditHours(request.getCredits() != null ? request.getCredits().intValue() : 3);
        if (request.getCourseId() != null) {
            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + request.getCourseId()));
            entity.setCourse(course);
        }
        return SubjectMapper.toResponse(repository.save(entity));
    }

    @Override
    public SubjectResponse getById(Long id) {
        Subject entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
        return SubjectMapper.toResponse(entity);
    }

    @Override
    public Page<SubjectResponse> getAll(String search, Pageable pageable) {
        syncSubjectsIfEmpty();
        return repository.findAllWithSearch(search, pageable)
                .map(SubjectMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Subject not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
