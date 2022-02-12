package com.coding.yo.security.service;

import com.coding.yo.entity.Member;
import com.coding.yo.repository.MemberRepository;
import com.google.firebase.auth.FirebaseAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {

        log.info("loadUserByUsername 진입");
        UserDetails userDetails = memberRepository.findByUid(uid).
                orElseThrow(() -> new UsernameNotFoundException("Can't find " + uid));

        return userDetails;
    }


    @Transactional
    public Member register(String uid, String email, String username, String profileUrl) {
        Member member = Member.builder()
                .uid(uid)
                .email(email)
                .username(username)
                .profileUrl(profileUrl)
                .build();
        log.info("member builder");

        memberRepository.save(member);
        log.info("member 저장");

        return member;
    }
}
