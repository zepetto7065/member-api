package com.member.api.service.member;

import com.member.api.domain.Member;
import com.member.api.port.out.persistence.MemberRepository;
import com.member.api.service.exception.MemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

class RegistMemberServiceTest {

    @InjectMocks
    private RegistMemberService registMemberService;

    @Mock
    private MemberRepository memberRepository;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("등록시 이미 있는 email, 멤버 등록")
    void alreadyExistEmailTest(){
        Member member = Member.builder()
                .email("test@gmail.com")
                .password("test1234")
                .build();
        Member alreadyMember = Member.builder()
                .email("test@gmail.com")
                .password("test1234567")
                .build();

        given(memberRepository.findByEmail(member.getEmail())).willReturn(Optional.ofNullable(alreadyMember));

        assertThrows(MemberException.class, () -> registMemberService.regist(member));
    }

    @Test
    @DisplayName("전화번호 인증이 안되고 멤버 등록")
    void notPhoneNumberCheckAndRegistMember(){
        Member member = Member.builder()
                .email("test@gmail.com")
                .password("test1234")
                .checkedPhoneNumber(false)
                .build();
        given(memberRepository.findByEmail(member.getEmail())).willReturn(Optional.empty());

        assertThrows(MemberException.class, () -> registMemberService.regist(member));
    }

}