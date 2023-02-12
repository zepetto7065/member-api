package com.member.api.payload;

import lombok.Data;

@Data
public class LoginResponseDto {
    Integer memberId;
    String token;
}
