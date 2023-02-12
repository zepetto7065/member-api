package com.member.api.converter;

import com.member.api.domain.Member;
import com.member.api.payload.LoginResponseDto;
import com.member.api.payload.MemberResponseDto;
import lombok.extern.java.Log;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginDtoConverter {
    LoginDtoConverter INSTANCE = Mappers.getMapper(LoginDtoConverter.class);
    LoginResponseDto map(Member member);
}
