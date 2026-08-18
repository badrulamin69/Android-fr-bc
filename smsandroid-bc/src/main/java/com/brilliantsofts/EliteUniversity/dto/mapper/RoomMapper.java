package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.RoomRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RoomResponse;
import com.brilliantsofts.EliteUniversity.entity.Room;

public class RoomMapper {
    public static Room toEntity(RoomRequest request) {
        Room entity = new Room();
        entity.setRoomNumber(request.getRoomNumber());
        entity.setFloor(request.getFloor());
        entity.setCapacity(request.getCapacity());
        entity.setCurrentOccupancy(request.getCurrentOccupancy());
        entity.setRoomType(request.getRoomType());
        entity.setMonthlyRent(request.getMonthlyRent());
        entity.setAvailable(request.isAvailable());
        entity.setHostelId(request.getHostelId());
        return entity;
    }

    public static RoomResponse toResponse(Room entity) {
        RoomResponse response = new RoomResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setRoomNumber(entity.getRoomNumber());
        response.setFloor(entity.getFloor());
        response.setCapacity(entity.getCapacity());
        response.setCurrentOccupancy(entity.getCurrentOccupancy());
        response.setRoomType(entity.getRoomType());
        response.setMonthlyRent(entity.getMonthlyRent());
        response.setAvailable(entity.isAvailable());
        response.setHostelId(entity.getHostelId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
