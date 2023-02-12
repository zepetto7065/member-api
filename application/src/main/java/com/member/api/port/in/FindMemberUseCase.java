package com.member.api.port.in;

import com.member.api.domain.Member;

public interface FindMemberUseCase {
    Member getMember(Integer member);

}
