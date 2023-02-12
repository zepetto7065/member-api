package com.member.api.service.login;

import com.member.api.domain.Member;
import com.member.api.port.out.persistence.MemberRepository;
import com.member.api.service.exception.LoginException;
import com.member.api.support.JwtProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private JwtProvider jwtProvider;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("정상 로그인")
    void loginTest(){
        String email = "sangmoon.yoo@gmail.com";
        String phoneNumber = "01091085420";
        Member member = Member.builder()
                .email(email)
                .name("tester")
                .password("testPassword")
                .phoneNumber(phoneNumber)
                .build();

        given(memberRepository.findByEmailOrPhoneNumber(email, phoneNumber)).willReturn(Optional.ofNullable(member));
        given(jwtProvider.getToken()).willReturn("jwtToken");

        Member ret = loginService.login(member);

        Assertions.assertThat(ret.getEmail()).isEqualTo(email);
        Assertions.assertThat(ret.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    @DisplayName("이메일이나 전화번호 정보 없음")
    void NoEmailOrPhoneNumberInfoTest(){
        String email = "sangmoon.yoo@gmail.com";
        String phoneNumber = "01091085420";
        Member member = Member.builder()
                .email(email)
                .name("tester")
                .password("testPassword")
                .phoneNumber(phoneNumber)
                .build();

        assertThrows(LoginException.class, () -> {
           loginService.login(member);
        });
    }

}