package com.coding.yo.security.service;

import com.coding.yo.repository.MemberRepository;
import com.coding.yo.security.entity.UserDetailsImpl;
import com.google.firebase.auth.FirebaseAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = memberRepository.findById(username).
                orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));

        return userDetails;
    }

    @Transactional
    public UserDetailsImpl register(String uid, String email, String nickname, String profileUrl) {
        UserDetailsImpl userDetailsImpl = UserDetailsImpl.builder()
                .username(uid)
                .email(email)
                .nickname(nickname)
                .profileUrl(profileUrl)
                .build();

        memberRepository.save(userDetailsImpl);
        return userDetailsImpl;
    }
}
