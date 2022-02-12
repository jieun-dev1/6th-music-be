package com.coding.yo.security.message.response;

import com.coding.yo.entity.Member;
import lombok.Getter;

@Getter
public class MemberInfo {
    private String uid;
    private String email;
    private String username;
    private String profileUrl;

    public MemberInfo(Member member) {
        this.uid = member.getUid();
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.profileUrl = member.getProfileUrl();

    }
}
