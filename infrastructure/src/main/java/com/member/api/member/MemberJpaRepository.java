package com.member.api.member;

import com.member.api.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberJpaRepository extends CrudRepository<Member, Integer> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailOrPhoneNumber(String email, String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}
