package com.mustache.bbs4.domain.dto;

import com.mustache.bbs4.domain.entity.User;
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

    public User toEntity(String encodingPassword) {
        return User.builder()
                .username(username)
                .password(encodingPassword)
                .role(UserRole.USER)
                .build();
    }
}
