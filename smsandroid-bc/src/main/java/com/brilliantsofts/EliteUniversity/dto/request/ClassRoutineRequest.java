package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class ClassRoutineRequest {
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
}
