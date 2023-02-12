package com.member.api.controller;

import com.member.api.converter.MemberDtoConverter;
import com.member.api.domain.Member;
import com.member.api.payload.AuthenticateRequestDto;
import com.member.api.payload.MemberResponseDto;
import com.member.api.payload.ModifyMemberRequestDto;
import com.member.api.payload.RegistMemberRequestDto;
import com.member.api.port.in.FindMemberUseCase;
import com.member.api.port.in.RegistMemberUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member API", description = "Member API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final RegistMemberUseCase registMemberUseCase;
    private final FindMemberUseCase findMemberUseCase;

    @GetMapping("/{memberId}")
    @Operation(summary = "멤버 정보 조회")
    public ResponseEntity<MemberResponseDto> getInfo(@PathVariable Integer memberId) {
        return ResponseEntity.ok(MemberDtoConverter.INSTANCE.map(findMemberUseCase.getMember(memberId)));
    }

    @PostMapping
    @Operation(summary = "멤버 정보 등록 (회원가입)")
    public ResponseEntity<MemberResponseDto> regist(@Validated @RequestBody RegistMemberRequestDto dto) {
        Member member = Member.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .name(dto.getName())
                .password(DigestUtils.sha256Hex(dto.getPassword()))
                .phoneNumber(dto.getPhoneNumber())
                .checkedPhoneNumber(dto.isCheckedPhoneNumber())
                .build();
        return ResponseEntity.ok(MemberDtoConverter.INSTANCE.map(registMemberUseCase.regist(member)));
    }

    @PutMapping
    @Operation(summary = "멤버 정보 수정")
    public ResponseEntity<MemberResponseDto> modifyPassword(@Validated @RequestBody ModifyMemberRequestDto dto){
        Member member = Member.builder()
                .email(dto.getEmail())
                .password(DigestUtils.sha256Hex(dto.getPassword()))
                .checkedPhoneNumber(dto.isCheckedPhoneNumber())
                .build();
        return ResponseEntity.ok(MemberDtoConverter.INSTANCE.map(registMemberUseCase.modifyPassword(member)));
    }

    @GetMapping("/{phoneNumber}/exist")
    @Operation(summary = "멤버 연락처 중복 여부 확인")
    public ResponseEntity<Boolean> existPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(registMemberUseCase.existPhoneNumber(phoneNumber));
    }

    @GetMapping("/authenticate/phone-numbers")
    @Operation(summary = "멤버 연락처 인증 코드 확인")
    public ResponseEntity<Boolean> authenticatePhoneNumber(@Validated AuthenticateRequestDto dto) {
        return ResponseEntity.ok(registMemberUseCase.authenticatePhoneNumber(dto.getCode()));
    }
}
