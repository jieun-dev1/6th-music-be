package com.coding.yo.security.message.request;

import com.coding.yo.entity.Member;
import lombok.Getter;

@Getter
public class MemberSignupRequestDto {
    private String uid;
    private String email;
    private String username;
    private String profileUrl;

    public MemberSignupRequestDto(Member member) {
        this.uid = member.getUid();
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.profileUrl = member.getProfileUrl();
    }
}
