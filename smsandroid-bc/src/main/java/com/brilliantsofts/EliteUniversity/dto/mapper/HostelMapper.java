package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.HostelRequest;
import com.brilliantsofts.EliteUniversity.dto.response.HostelResponse;
import com.brilliantsofts.EliteUniversity.entity.Hostel;

public class HostelMapper {
    public static Hostel toEntity(HostelRequest request) {
        Hostel entity = new Hostel();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setType(request.getType());
        entity.setAddress(request.getAddress());
        entity.setWardensName(request.getWardensName());
        entity.setWardensPhone(request.getWardensPhone());
        entity.setTotalRooms(request.getTotalRooms());
        return entity;
    }

    public static HostelResponse toResponse(Hostel entity) {
        HostelResponse response = new HostelResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setType(entity.getType());
        response.setAddress(entity.getAddress());
        response.setWardensName(entity.getWardensName());
        response.setWardensPhone(entity.getWardensPhone());
        response.setTotalRooms(entity.getTotalRooms());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
