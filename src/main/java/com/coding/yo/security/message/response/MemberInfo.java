package com.coding.yo.security.message.response;

import com.coding.yo.security.UserDetailsImpl;
import lombok.Getter;

@Getter
public class MemberInfo {
    private String uid;
    private String email;
    private String nickname;

    public MemberInfo(UserDetailsImpl userDetailsImpl) {
        this.uid = userDetailsImpl.getUsername();
        this.email = userDetailsImpl.getEmail();
        this.nickname = userDetailsImpl.getNickname();
    }
}
