package com.member.api.converter;

import com.member.api.domain.Member;
import com.member.api.payload.MemberResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberDtoConverter {
    MemberDtoConverter INSTANCE = Mappers.getMapper(MemberDtoConverter.class);
    MemberResponseDto map(Member member);
}
