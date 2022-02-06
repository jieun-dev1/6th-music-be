package com.coding.yo.filter;

import com.coding.yo.util.RequestUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Profile;
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

    private UserDetailsService userDetailsService;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        FirebaseToken decodedToken;
        //Invalid Header - Authorization이라는 key값의 value가 없거나 bearer 로 시작하지 않을 경우
//        if (header == null || !header.startsWith("Bearer ")) {
//            setUnauthorizedResponse(response, "INVALID HEADER");
//            return;
//        }
        //token: bearer 이후의 값
//        String token = header.substring(7);
        //INVALID TOKEN -verifyIdToken을 통과하지 못했을 때

        try{
            String token = RequestUtil.getAuthorizationToken(request.getHeader("Authorization"));
            decodedToken = firebaseAuth.verifyIdToken(token);
        } catch (FirebaseAuthException | IllegalArgumentException e) {
//            setUnauthorizedResponse(response, "INVALID TOKEN");
            //ErrorMessage 응답 전송
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            //write: writes a string
            response.getWriter().write("{\"code\":\"INVALID TOKEN\", \"message\":\"" +e.getMessage()+"\"}");
            return;
        }

        //USER를 가져와 SecurityContext에 저장
        //getContext.setAuthentication(authentication)에서 user 를 넣어주게 됨.
        try{
            UserDetails user = userDetailsService.loadUserByUsername(decodedToken.getUid());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(NoSuchElementException e) {

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
