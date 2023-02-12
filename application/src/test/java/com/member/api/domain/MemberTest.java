package com.member.api.domain;

import com.member.api.service.exception.LoginException;
import com.member.api.service.exception.MemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("패스워드가 다른 경우")
    void do_not_same_password() {
        String targetPassowrd = "testPasswordSecond";
        Member member = Member.builder()
                    .email("test@gmail.com")
                    .password("testPassword")
                    .build();

        assertThrows(LoginException.class, () ->{
            member.checkPassword(targetPassowrd);
        });
    }

    @Test
    @DisplayName("전화번호 인증이 안되고 패스워드 수정 적용")
    void not_check_phonenumber() {
        Member member = Member.builder()
                .email("test@gmail.com")
                .password("originPassword")
                .build();

        Member target = Member.builder()
                .email("test@gmail.com")
                .password("modifiedPassword")
                .checkedPhoneNumber(false)
                .build();

        assertThrows(MemberException.class, ()->{
            member.apply(target);
        });
    }
}