package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.VideoLectureRequest;
import com.brilliantsofts.EliteUniversity.dto.response.VideoLectureResponse;
import com.brilliantsofts.EliteUniversity.entity.VideoLecture;
import com.brilliantsofts.EliteUniversity.dto.mapper.VideoLectureMapper;
import com.brilliantsofts.EliteUniversity.repository.CourseModuleRepository;
import com.brilliantsofts.EliteUniversity.repository.VideoLectureRepository;
import com.brilliantsofts.EliteUniversity.service.VideoLectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoLectureServiceImpl implements VideoLectureService {
    @Autowired
    private VideoLectureRepository repository;
    @Autowired
    private CourseModuleRepository courseModuleRepository;

    @Override
    public VideoLectureResponse create(VideoLectureRequest request) {
        VideoLecture entity = VideoLectureMapper.toEntity(request);
        if (request.getModuleId() != null) entity.setModule(courseModuleRepository.findById(request.getModuleId()).orElse(null));
        return VideoLectureMapper.toResponse(repository.save(entity));
    }
    @Override
    public VideoLectureResponse update(Long id, VideoLectureRequest request) {
        VideoLecture entity = repository.findById(id).orElseThrow(() -> new RuntimeException("VideoLecture not found"));
        entity.setTitle(request.getTitle());
        entity.setVideoUrl(request.getVideoUrl());
        entity.setDurationMinutes(request.getDurationMinutes());
        if (request.getModuleId() != null) entity.setModule(courseModuleRepository.findById(request.getModuleId()).orElse(null));
        return VideoLectureMapper.toResponse(repository.save(entity));
    }
    @Override
    public VideoLectureResponse getById(Long id) {
        return VideoLectureMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("VideoLecture not found")));
    }
    @Override
    public List<VideoLectureResponse> getAll() {
        return repository.findAll().stream().map(VideoLectureMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<VideoLectureResponse> getByModule(Long moduleId) {
        return repository.findByModuleId(moduleId).stream().map(VideoLectureMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
