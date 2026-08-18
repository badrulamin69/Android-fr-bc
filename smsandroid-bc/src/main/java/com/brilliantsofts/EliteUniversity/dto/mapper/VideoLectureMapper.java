package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.VideoLectureRequest;
import com.brilliantsofts.EliteUniversity.dto.response.VideoLectureResponse;
import com.brilliantsofts.EliteUniversity.entity.VideoLecture;

public class VideoLectureMapper {
    public static VideoLecture toEntity(VideoLectureRequest request) {
        VideoLecture entity = new VideoLecture();
        entity.setTitle(request.getTitle());
        entity.setVideoUrl(request.getVideoUrl());
        entity.setDurationMinutes(request.getDurationMinutes());
        return entity;
    }

    public static VideoLectureResponse toResponse(VideoLecture entity) {
        VideoLectureResponse response = new VideoLectureResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setVideoUrl(entity.getVideoUrl());
        response.setDurationMinutes(entity.getDurationMinutes());
        if (entity.getModule() != null) {
            response.setModuleId(entity.getModule().getId());
            response.setModuleTitle(entity.getModule().getModuleTitle());
        }
        return response;
    }
}
