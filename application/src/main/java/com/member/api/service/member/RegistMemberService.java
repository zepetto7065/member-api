package com.member.api.service.member;

import com.member.api.domain.Member;
import com.member.api.service.exception.MemberException;
import com.member.api.port.in.RegistMemberUseCase;
import com.member.api.port.out.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RegistMemberService implements RegistMemberUseCase {

    private final MemberRepository memberRepository;

    @Override
    public Member regist(Member member) {
        if(memberRepository.findByEmail(member.getEmail()).isPresent()) throw new MemberException("이미 존재하는 email 입니다.");
        if(!member.isCheckedPhoneNumber()) throw new MemberException("전화번호 인증이 되지 않았습니다.");
        return memberRepository.save(member);
    }

    @Override
    public Member modifyPassword(Member target) {
        Member member = memberRepository.findByEmail(target.getEmail()).orElseThrow(() -> new MemberException("존재하지 않는 email 입니다."));
        member.applyPassword(target.getPassword());
        return memberRepository.save(member);
    }

    @Override
    public boolean existPhoneNumber(String phoneNumber) {
        return memberRepository.existByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean authenticatePhoneNumber(String code) {
        //TODO: 외부 모듈 code 유효성 체크
        //true -> 인증, false -> 인증실패
        return true;
    }
}
