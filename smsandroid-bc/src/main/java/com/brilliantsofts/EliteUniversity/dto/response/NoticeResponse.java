package com.brilliantsofts.EliteUniversity.dto.response;

import com.brilliantsofts.EliteUniversity.enums.NoticeAudience;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NoticeResponse {
    private Long id;
    private String title;
    private String content;
    private String attachmentUrl;
    private LocalDateTime publishDate;
    private LocalDateTime expiryDate;
    private boolean published;
    private NoticeAudience audience;
    private Long createdById;
    private String createdByName;
    private Long facultyId;
    private String facultyName;
    private Long departmentId;
    private String departmentName;
}
