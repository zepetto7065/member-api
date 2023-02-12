package com.member.api.port.in;

import com.member.api.domain.Member;

public interface LoginUseCase {
    Member login(Member member);
}
