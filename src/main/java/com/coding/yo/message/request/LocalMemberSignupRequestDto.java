package com.coding.yo.message.request;


import com.coding.yo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocalMemberSignupRequestDto {
    private String uid;
    private String email;
    private String username;
    private String profileUrl;


    public LocalMemberSignupRequestDto(Member member) {
        this.uid = member.getUid();
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.profileUrl = member.getProfileUrl();
    }
}

