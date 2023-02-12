package com.member.api.port.out.persistence;

import com.member.api.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(Integer memberId);
    Member save(Member command);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailOrPhoneNumber(String email, String phoneNumber);
    boolean existByPhoneNumber(String phoneNumber);

}
