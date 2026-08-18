package com.brilliantsofts.EliteUniversity.dto.request;

import com.brilliantsofts.EliteUniversity.enums.UserRole;
import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private String phone;
    private boolean enabled = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private UserRole role;
}
