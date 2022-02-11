//package com.coding.yo.security.entity;
//
//import com.coding.yo.entity.Member;
//import io.grpc.netty.shaded.io.netty.handler.codec.socks.SocksAuthRequest;
//import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.Collection;
//
//@Entity
//@Getter @Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class UserDetailsImpl implements UserDetails {
//    //member를 final로 주입할 수 있을까? (중복 제거) - 리팩토링 시 반영
//
//    @Id
//    private String uid;
//    @Column
//    private String username;
//    @Column
//    private String email;
////    @Column
////    private String nickname;
//    @Column
//    private String profileUrl;
//
//
////    @Builder
////    public UserDetailsImpl(String username, String email, String nickname, String profileUrl ) {
////    }
//
//
//    //implement해야하는 필수 method
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    //OAuth라 return null인가?
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
