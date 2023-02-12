package com.member.api.service.member;

import com.member.api.domain.Member;
import com.member.api.service.exception.MemberException;
import com.member.api.port.in.FindMemberUseCase;
import com.member.api.port.out.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class FindMemberService implements FindMemberUseCase {

    private final MemberRepository memberRepository;

    @Override
    public Member getMember(Integer memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new MemberException("회원이 존재하지 않습니다."));
    }
}
