package com.member.api.controller;

import com.member.api.converter.MemberDtoConverter;
import com.member.api.domain.Member;
import com.member.api.payload.LoginRequestDto;
import com.member.api.payload.MemberResponseDto;
import com.member.api.port.in.LoginUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Login API", description = "Login API")
@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping
    @Operation(summary = "로그인")
    public ResponseEntity<MemberResponseDto> login(@Validated @RequestBody LoginRequestDto dto){
        Member member = Member.builder()
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .password(DigestUtils.sha256Hex(dto.getPassword()))
                .build();
        return ResponseEntity.ok(MemberDtoConverter.INSTANCE.map(loginUseCase.login(member)));
    }
}
