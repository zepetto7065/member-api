package com.member.api.payload;

import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Value
public class AuthenticateRequestDto {
    @NotNull
    @Size(max = 4)
    String code;
}
