package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class MenuResponse {
    private Long id;
    private String title;
    private String icon;
    private String route;
    private Integer orderNo;
    private String permissionCode;
    private String module;
    private boolean visible;
    private MenuParentResponse parent;
    private List<MenuResponse> children;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;

    @Data
    public static class MenuParentResponse {
        private Long id;
        private String title;
    }
}
