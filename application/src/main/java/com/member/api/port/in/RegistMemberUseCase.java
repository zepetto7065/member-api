package com.member.api.port.in;

import com.member.api.domain.Member;

public interface RegistMemberUseCase {
    Member regist(Member member);
    Member modifyPassword(Member member);
    boolean existPhoneNumber(String phoneNumber);
    boolean authenticatePhoneNumber(String code);
}
