package com.coding.yo.security.filter;

import com.coding.yo.exception.CustomException;
import com.coding.yo.repository.MemberRepository;
import com.coding.yo.util.RequestUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
public class LocalJwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("로그인 시작");
        String token;
        try {
            token = RequestUtil.getAuthorizationToken(request.getHeader("Authorization"));
            log.info("로그인 토큰" + token);
        } catch (CustomException e) {
            //ErrorMessage 응답 전송
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            //write: writes a string
            response.getWriter().write("{\"code\":\"INVALID TOKEN2\", \"message\":\"" + e.getMessage() + "\"}");
            return;
        }

        try {
            UserDetails user = userDetailsService.loadUserByUsername(token); //header-uid 로 user 찾아오기..
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (UsernameNotFoundException e) {

            /**
             *ErrorMessage 응답 전송
             *에러를 401에서 404로 변경
             *401: UNAuthorized (클라이언트가 해당 리소스에 대한 인증 필요)
             *404: 요청 리소스가 서버에 없거나, 클라이언트가 궈한 부족한 리소스 접근 시.
             */

            response.setStatus(HttpStatus.SC_NOT_FOUND);
            response.setContentType("application/json");
            response.getWriter().write("{\"code\":\"USER_NOT_FOUND\"}");

            return;
        }
        filterChain.doFilter(request, response);
    }
}
