package com.member.api.service.login;

import com.member.api.domain.Member;
import com.member.api.port.in.LoginUseCase;
import com.member.api.port.out.persistence.MemberRepository;
import com.member.api.service.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final MemberRepository memberRepository;

    @Override
    public Member login(Member target) {
        Member member = memberRepository.findByEmailOrPhoneNumber(target.getEmail(), target.getPhoneNumber()).orElseThrow(() -> new LoginException("존재하지 않는 회원 정보입니다."));
        return member.checkPassword(target.getPassword());
    }
}
