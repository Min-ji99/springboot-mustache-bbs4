package com.mustache.bbs4.domain.dto;

import com.mustache.bbs4.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserJoinRequest {
    private String username;
    private String password;
}
