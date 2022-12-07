package com.mustache.bbs4.service;

import com.mustache.bbs4.domain.dto.UserJoinRequest;
import com.mustache.bbs4.domain.dto.UserJoinResponse;
import com.mustache.bbs4.domain.dto.UserLoginRequest;
import com.mustache.bbs4.domain.entity.User;
import com.mustache.bbs4.domain.enums.UserRole;
import com.mustache.bbs4.exception.AppException;
import com.mustache.bbs4.exception.ErrorCode;
import com.mustache.bbs4.repository.UserRepository;
import com.mustache.bbs4.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;

    private long expireTimeMs=1000*60*60;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public String login(UserLoginRequest userLoginRequest) {
        User user=userRepository.findByUsername(userLoginRequest.getUsername())
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND_USER_NAME, String.format("username %s가 존재하지 않습니다.", userLoginRequest.getUsername())));

        if(!encoder.matches(userLoginRequest.getPassword(), user.getPassword())){
            throw new AppException(ErrorCode.INVALID_PASSWORD, String.format("username 또는 password가 틀렸습니다."));
        }
        return JwtTokenUtil.createToken(user.getUsername(), secretKey, expireTimeMs);
    }

    public User getUsername(String username) {
        User user=userRepository.findByUsername(username)
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND_USER_NAME, String.format("username %s가 존재하지 않습니다.", username)));
        return user;
    }

    public UserJoinResponse join(UserJoinRequest userJoinRequest) {
        userRepository.findByUsername(userJoinRequest.getUsername())
                .ifPresent(user->{
                    throw new AppException(ErrorCode.DUPLICATED_USER_NAME, String.format("이미 존재하는 %s입니다.", userJoinRequest.getUsername()));
                });
        String encodingPassword= encoder.encode(userJoinRequest.getPassword());
        User user = userJoinRequest.toEntity(encodingPassword);

        User savedUser = userRepository.save(user);

        return UserJoinResponse.builder()
                .username(savedUser.getUsername())
                .message("회원가입 성공")
                .build();
    }
}
