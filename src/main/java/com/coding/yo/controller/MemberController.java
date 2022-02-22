package com.coding.yo.controller;

import com.coding.yo.entity.Member;
import com.coding.yo.security.message.request.LocalMemberSignupRequestDto;
import com.coding.yo.security.message.request.MemberSignupRequestDto;
import com.coding.yo.security.service.UserDetailsServiceImpl;
import com.coding.yo.util.RequestUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class MemberController {
    private final FirebaseAuth firebaseAuth;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * 수정 필요: 이미 예외처리를 했기 때문에 ResponseEntity로 안가져와도 무관함
     * 수정 필요: MemberService 로 분리 리팩토링
     * 수정 필요:이미 키가 있는 상황일 때 자체 에러 처리 필요
     * 로컬 서버 회원 가입 - Front 에서 Authorization Header의 값이 전달 되는 과정 생략.
     * @param
     * @return
     */

    //로컬 서버 회원 가입
    @PostMapping("/local")
    public ResponseEntity<LocalMemberSignupRequestDto> localRegister(@RequestBody LocalMemberSignupRequestDto localDto) {
        Member registeredUser = userDetailsServiceImpl.register
                (localDto.getUid(), localDto.getEmail(), localDto.getUsername(), localDto.getProfileUrl());
        return new ResponseEntity<>(new LocalMemberSignupRequestDto(registeredUser), HttpStatus.CREATED);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new LocalMemberSignupRequestDto(registeredUser));
    }

    //회원가입
    @PostMapping("")
    public ResponseEntity<MemberSignupRequestDto> register(@RequestHeader("Authorization") String authorization) {

        //Token 가져온다
        FirebaseToken decodedToken;
        try {
            String token = RequestUtil.getAuthorizationToken(authorization);
            decodedToken = firebaseAuth.verifyIdToken(token);

        } catch (IllegalArgumentException | FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }
        log.info("controller 회원가입 decoded: {} {} {} {}", decodedToken.getUid(), decodedToken.getEmail(), decodedToken.getName(), decodedToken.getPicture());
        //사용자를 등록한다.
        Member registeredUser = userDetailsServiceImpl.register(decodedToken.getUid(), decodedToken.getEmail(), decodedToken.getName(), decodedToken.getPicture());
        log.info("userDetailsServiceImpl 등록");
        return new ResponseEntity<>(new MemberSignupRequestDto(registeredUser), HttpStatus.CREATED);
//        return new MemberInfo;
    }

    //로그인
    @GetMapping("/me")
    public ResponseEntity<MemberSignupRequestDto> login(Authentication authentication) {
        Member member = ((Member) authentication.getPrincipal());
        log.info("member - 로그인 성공" + member.getUsername());
        return ResponseEntity.ok(new MemberSignupRequestDto(member));
    }
}

