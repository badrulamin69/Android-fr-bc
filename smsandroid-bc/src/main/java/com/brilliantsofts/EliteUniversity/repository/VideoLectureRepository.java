package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.VideoLecture;

import java.util.List;

public interface VideoLectureRepository extends org.springframework.data.jpa.repository.JpaRepository<VideoLecture, Long> {
    List<VideoLecture> findByModuleId(Long moduleId);
}
