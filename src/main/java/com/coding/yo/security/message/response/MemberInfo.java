package com.coding.yo.security.message.response;

import com.coding.yo.security.entity.UserDetailsImpl;
import lombok.Getter;

@Getter
public class MemberInfo {
    private String uid;
    private String email;
    private String nickname;
    private String profileUrl;

    public MemberInfo(UserDetailsImpl userDetailsImpl) {
        this.uid = userDetailsImpl.getUsername();
        this.email = userDetailsImpl.getEmail();
        this.nickname = userDetailsImpl.getNickname();
        this.profileUrl = userDetailsImpl.getProfileUrl();

    }
}
