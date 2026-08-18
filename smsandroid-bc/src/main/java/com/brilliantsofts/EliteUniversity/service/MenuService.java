package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.MenuRequest;
import com.brilliantsofts.EliteUniversity.dto.response.MenuResponse;

import java.util.List;

public interface MenuService {
    MenuResponse create(MenuRequest request);
    MenuResponse update(Long id, MenuRequest request);
    MenuResponse getById(Long id);
    List<MenuResponse> getAll();
    List<MenuResponse> getRootMenus();
    List<MenuResponse> getChildren(Long parentId);
    List<MenuResponse> getMyMenus(Long userId);
    List<MenuResponse> getMyMenusForUsername(String username);
    void delete(Long id);
}
