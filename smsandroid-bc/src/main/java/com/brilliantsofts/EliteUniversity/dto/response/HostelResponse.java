package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HostelResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String code;
    private String type;
    private String address;
    private String wardensName;
    private String wardensPhone;
    private Integer totalRooms;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
