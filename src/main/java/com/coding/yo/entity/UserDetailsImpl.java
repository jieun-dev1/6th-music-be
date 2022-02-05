package com.coding.yo.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
    //member 생성되면 member를 final로 주입
    //Getter는 메소드로 생성
    private final Member member;
    public Member getMember() {return member;}

    //implement해야하는 필수 method
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //OAuth라 return null인가?
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
