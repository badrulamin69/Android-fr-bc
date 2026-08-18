package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.MenuRequest;
import com.brilliantsofts.EliteUniversity.dto.response.MenuResponse;
import com.brilliantsofts.EliteUniversity.entity.Menu;

import java.util.ArrayList;

public class MenuMapper {
    public static Menu toEntity(MenuRequest request) {
        Menu entity = new Menu();
        entity.setTitle(request.getTitle());
        entity.setIcon(request.getIcon());
        entity.setRoute(request.getRoute());
        entity.setOrderNo(request.getOrderNo());
        entity.setPermissionCode(request.getPermissionCode());
        entity.setModule(request.getModule());
        entity.setVisible(request.isVisible());
        return entity;
    }

    public static MenuResponse toResponse(Menu entity) {
        MenuResponse response = new MenuResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setIcon(entity.getIcon());
        response.setRoute(entity.getRoute());
        response.setOrderNo(entity.getOrderNo());
        response.setPermissionCode(entity.getPermissionCode());
        response.setModule(entity.getModule());
        response.setVisible(entity.isVisible());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getParent() != null) {
            MenuResponse.MenuParentResponse parentSummary = new MenuResponse.MenuParentResponse();
            parentSummary.setId(entity.getParent().getId());
            parentSummary.setTitle(entity.getParent().getTitle());
            response.setParent(parentSummary);
        }
        if (entity.getChildren() != null) {
            response.setChildren(entity.getChildren().stream()
                    .map(MenuMapper::toResponse)
                    .toList());
        } else {
            response.setChildren(new ArrayList<>());
        }
        return response;
    }
}
