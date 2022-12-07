package com.mustache.bbs4.domain.dto;

import com.mustache.bbs4.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserJoinResponse {
    private String username;
    private String message;
}
