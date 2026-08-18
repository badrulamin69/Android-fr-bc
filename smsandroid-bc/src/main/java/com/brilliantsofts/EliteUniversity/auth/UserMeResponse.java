package com.brilliantsofts.EliteUniversity.auth;

import com.brilliantsofts.EliteUniversity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMeResponse {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private UserRole role;
    private boolean enabled;
    private boolean emailVerified;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
}
