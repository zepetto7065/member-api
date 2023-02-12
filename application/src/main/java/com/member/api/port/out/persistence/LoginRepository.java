package com.member.api.port.out.persistence;

import com.member.api.domain.Member;

import java.util.Optional;

public interface LoginRepository {
    Optional<Member> findByEmail(String email);
}
