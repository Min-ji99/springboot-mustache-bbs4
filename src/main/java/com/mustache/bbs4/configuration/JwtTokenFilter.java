package com.mustache.bbs4.configuration;

import com.mustache.bbs4.domain.entity.User;
import com.mustache.bbs4.service.UserService;
import com.mustache.bbs4.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final String secretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorizationHeader);
        if(authorizationHeader==null || !authorizationHeader.startsWith("Bearer ")){
            log.error("헤더를 가져오는 과정에서 오류 발생");
            filterChain.doFilter(request, response);
            return;
        }
        String token;
        try{
            token=authorizationHeader.split(" ")[1].trim();
        }catch(Exception e){
            log.error("token 추출 실패");
            filterChain.doFilter(request, response);
            return;
        }
        if(JwtTokenUtil.isExpired(token, secretKey)){
            log.error("token 유효기간 만료");
            filterChain.doFilter(request, response);
            return;
        }
        String username=JwtTokenUtil.getUsername(token, secretKey);

        User user=userService.getUsername(username);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, List.of(new SimpleGrantedAuthority("USER")));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
