package com.member.api.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Value
public class ModifyMemberRequestDto {
    @NotNull
    @Schema(description = "사용자 이메일")
    String email;

    @NotNull
    @Schema(description = "사용자 비밀번호")
    String password;

    @NotNull
    @Schema(description = "전화번호 인증여부")
    boolean checkedPhoneNumber;
}
