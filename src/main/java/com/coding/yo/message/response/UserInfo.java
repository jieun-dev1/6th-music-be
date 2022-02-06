package com.coding.yo.message.response;

import com.coding.yo.entity.UserDetailsImpl;
import lombok.Getter;

@Getter
public class UserInfo {
    private String uid;
    private String email;
    private String nickname;

    public UserInfo(UserDetailsImpl userDetailsImpl) {
        this.uid = userDetailsImpl.getUsername();
        this.email = userDetailsImpl.getMember().getEmail();
        this.nickname = userDetailsImpl.getMember().getNickname();
    }
}
