package com.coding.yo.security.filter;

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
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final FirebaseAuth firebaseAuth;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        FirebaseToken decodedToken;
        log.info("로그인 시작");
        try {
            String token = RequestUtil.getAuthorizationToken(request.getHeader("Authorization"));
            log.info("로그인 토큰" + token);
            decodedToken = firebaseAuth.verifyIdToken(token);
        } catch (FirebaseAuthException | IllegalArgumentException e) {
//            setUnauthorizedResponse(response, "INVALID TOKEN");
            //ErrorMessage 응답 전송
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            //write: writes a string
            response.getWriter().write("{\"code\":\"INVALID TOKEN2\", \"message\":\"" + e.getMessage() + "\"}");
            return;
        }

        //USER를 가져와 SecurityContext에 저장
        //getContext.setAuthentication(authentication)에서 user 를 넣어주게 됨.
        try {
            log.info("decoded: {} {} {}", decodedToken.getUid(), decodedToken.getEmail(), decodedToken.getPicture());
            UserDetails user = userDetailsService.loadUserByUsername(decodedToken.getUid());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (NoSuchElementException e) {

            //ErrorMessage 응답 전송
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            //write: writes a string
            response.getWriter().write("{\"code\":\"USER_NOT_FOUND\"}");
            return;
        }
        filterChain.doFilter(request, response);
    }


    //Get the token from the request

}
