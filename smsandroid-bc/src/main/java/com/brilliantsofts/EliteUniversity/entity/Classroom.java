package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "classrooms")
@Data
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long buildingId;

    private String roomNumber;

    private Integer floor;

    private Integer capacity;

    private String roomType;

    private boolean isLab;

    private boolean isSmartClassroom;

    private boolean hasProjector;

    private boolean hasWhiteboard;

    private boolean hasWifi;

    private String equipment;

    private boolean isAvailable;

    private boolean isActive;

    private String remarks;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
