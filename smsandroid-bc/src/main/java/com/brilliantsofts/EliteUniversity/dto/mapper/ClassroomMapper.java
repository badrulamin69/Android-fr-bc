package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ClassroomRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ClassroomResponse;
import com.brilliantsofts.EliteUniversity.entity.Classroom;

public class ClassroomMapper {
    public static Classroom toEntity(ClassroomRequest request) {
        Classroom entity = new Classroom();
        entity.setBuildingId(request.getBuildingId());
        entity.setRoomNumber(request.getRoomNumber());
        entity.setFloor(request.getFloor());
        entity.setCapacity(request.getCapacity());
        entity.setRoomType(request.getRoomType());
        entity.setLab(request.isLab());
        entity.setSmartClassroom(request.isSmartClassroom());
        entity.setHasProjector(request.isHasProjector());
        entity.setHasWhiteboard(request.isHasWhiteboard());
        entity.setHasWifi(request.isHasWifi());
        entity.setEquipment(request.getEquipment());
        entity.setAvailable(request.isAvailable());
        entity.setActive(request.isActive());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static ClassroomResponse toResponse(Classroom entity) {
        ClassroomResponse response = new ClassroomResponse();
        response.setId(entity.getId());
        response.setBuildingId(entity.getBuildingId());
        response.setRoomNumber(entity.getRoomNumber());
        response.setFloor(entity.getFloor());
        response.setCapacity(entity.getCapacity());
        response.setRoomType(entity.getRoomType());
        response.setLab(entity.isLab());
        response.setSmartClassroom(entity.isSmartClassroom());
        response.setHasProjector(entity.isHasProjector());
        response.setHasWhiteboard(entity.isHasWhiteboard());
        response.setHasWifi(entity.isHasWifi());
        response.setEquipment(entity.getEquipment());
        response.setAvailable(entity.isAvailable());
        response.setActive(entity.isActive());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
