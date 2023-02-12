package com.member.api.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Getter
@Value
public class LoginRequestDto {
    @Schema(description = "사용자 이메일")
    String email;

    @Schema(description = "사용자 전화번호")
    String phoneNumber;

    @NotNull
    @Schema(description = "사용자 비밀번호")
    String password;
}
