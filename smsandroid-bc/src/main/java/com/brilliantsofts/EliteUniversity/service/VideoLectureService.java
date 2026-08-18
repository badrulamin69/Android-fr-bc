package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.VideoLectureRequest;
import com.brilliantsofts.EliteUniversity.dto.response.VideoLectureResponse;

import java.util.List;

public interface VideoLectureService {
    VideoLectureResponse create(VideoLectureRequest request);
    VideoLectureResponse update(Long id, VideoLectureRequest request);
    VideoLectureResponse getById(Long id);
    List<VideoLectureResponse> getAll();
    List<VideoLectureResponse> getByModule(Long moduleId);
    void delete(Long id);
}
