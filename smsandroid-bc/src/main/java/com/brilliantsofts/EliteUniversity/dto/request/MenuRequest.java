package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class MenuRequest {
    private String title;
    private String icon;
    private String route;
    private Integer orderNo;
    private String permissionCode;
    private String module;
    private boolean visible;
    private Long parentId;
}
