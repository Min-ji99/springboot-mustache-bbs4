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
        //username이 존재하지 않으면 예외처리
        User user=userRepository.findByUsername(userLoginRequest.getUsername())
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND_USER_NAME, String.format("username %s가 존재하지 않습니다.", userLoginRequest.getUsername())));

        //로그인하려는 user의 암호와 입력한 암호가 다르면 예외처리
        //matches() 메서드는 첫번째 파라미터는 입력한 값, 두번째는 암호화된 값을 전달
        //순서 바뀌면 두 암호가 다르다고 판단함
        if(!encoder.matches(userLoginRequest.getPassword(), user.getPassword())){
            throw new AppException(ErrorCode.INVALID_PASSWORD, String.format("username 또는 password가 틀렸습니다."));
        }
        //토큰 발급
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
        //password 암호화
        String encodingPassword= encoder.encode(userJoinRequest.getPassword());
        //암호화한 password로 Entity로 변경
        User user = userJoinRequest.toEntity(encodingPassword);

        User savedUser = userRepository.save(user);

        return UserJoinResponse.builder()
                .username(savedUser.getUsername())
                .message("회원가입 성공")
                .build();
    }
}
