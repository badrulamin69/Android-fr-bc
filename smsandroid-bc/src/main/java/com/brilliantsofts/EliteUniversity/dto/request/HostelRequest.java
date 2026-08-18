package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class HostelRequest {
    private String name;
    private String code;
    private String type;
    private String address;
    private String wardensName;
    private String wardensPhone;
    private Integer totalRooms;
}
