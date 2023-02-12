package com.member.api.member;

import com.member.api.domain.Member;
import com.member.api.port.out.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class MemberRepositoryAdapter implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Optional<Member> findById(Integer memberId) {
        return memberJpaRepository.findById(memberId);
    }

    @Override
    public Member save(Member command) {
        return memberJpaRepository.save(command);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<Member> findByEmailOrPhoneNumber(String email, String phoneNumber) {
        return memberJpaRepository.findByEmailOrPhoneNumber(email, phoneNumber);
    }

    @Override
    public boolean existByPhoneNumber(String phoneNumber) {
        return memberJpaRepository.existsByPhoneNumber(phoneNumber);
    }
}
