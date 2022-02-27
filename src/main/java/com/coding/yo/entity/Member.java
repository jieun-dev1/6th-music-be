package com.coding.yo.entity;

import com.coding.yo.entity.audit.AuditListener;
import com.coding.yo.entity.audit.Auditable;
import com.coding.yo.entity.audit.TimeColumns;
import com.coding.yo.util.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
@EntityListeners(AuditListener.class)
public class Member implements Auditable, UserDetails {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //추가
    @Column(unique = true)
    private String uid;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(name = "profile_url", nullable = false)
    private String profileUrl;

    @Column(name = "user_role", columnDefinition = "varchar(10) default 'USER'")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> post;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CommentLike> commentLikes;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CommentReport> commentReports;

    @Embedded
    private TimeColumns timeColumns;


    @Builder
    public Member(String username, String email, String profileUrl, String uid) {
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

    @Override
    public void setTimeColumns(TimeColumns timeColumns) {
        this.timeColumns = timeColumns;
    }
}