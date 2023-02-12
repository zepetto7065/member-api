package com.member.api.payload;

import lombok.Data;

@Data
public class MemberResponseDto {
    Integer memberId;
    String email;
    String name;
    String nickname;
    String phoneNumber;
}
