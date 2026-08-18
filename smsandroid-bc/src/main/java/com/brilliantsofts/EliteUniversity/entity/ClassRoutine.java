package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "class_routines")
@Data
public class ClassRoutine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long subjectId;

    private Long administrationId;

    private Long sectionId;

    private Long semesterId;

    private Long batchId;

    private Long timeSlotId;

    private Long classroomId;

    private String dayOfWeek;

    private String startTime;

    private String endTime;

    private String room;

    private String building;

    private String classType;

    private String shift;

    private boolean isActive;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
