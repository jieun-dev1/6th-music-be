package com.coding.yo.entity;

import com.coding.yo.util.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member implements UserDetails {

    private String provider;
    private String providerId;

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //추가
    @Column(unique = true)
    private String uid;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 10)
    private String username;

    //Firebase OAuth의 필수값이어서 추가함
//    @Column
//    private String nickname;


    @Column(name = "profile_url", nullable = false)
    private String profileUrl;

    @Column(name = "user_role", columnDefinition = "varchar(10) default 'USER'")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> post;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CommentLike> commentLikes;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CommentReport> commentReports;

    @Embedded
    private TimeColumns timeColumns;


    @Builder
    public Member(String provider, String providerId, String username, String email, String profileUrl, String uid) {
        this.provider = provider;
        this.providerId = providerId;
        this.username = username;
        this.email = email;
        this.profileUrl = profileUrl;
        this.uid = uid;


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
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