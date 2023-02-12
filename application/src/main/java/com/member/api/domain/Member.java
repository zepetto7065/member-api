package com.member.api.domain;

import com.member.api.service.exception.LoginException;
import com.member.api.service.exception.MemberException;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity{

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;
    private String email;
    private String nickname;
    private String password;
    private String name;
    private String phoneNumber;
    @Transient
    private boolean checkedPhoneNumber;
    @Transient
    private String token;
    private static Member of(Member member){
        return new Member(member.getMemberId(), member.getEmail(), member.getNickname(), member.getPassword(), member.getName(), member.getPhoneNumber(), true, null);
    }

    private static Member ofAthenticate(Member member){
        return new Member(member.getMemberId(), member.getEmail(), member.getNickname(), member.getPassword(), member.getName(), member.getPhoneNumber(), true, member.getToken());
    }

    public Member checkPassword(String targetPassword) {
        if(!targetPassword.equals(this.getPassword())){
            throw new LoginException("비밀번호가 일치하지 않습니다.");
        }
        return Member.of(this);
    }

    public Member apply(Member target) {
        if(!target.isCheckedPhoneNumber()) throw new MemberException("전화번호 인증이 되지 않았습니다.");
        return Member.of(target);
    }

    public Member applyToken(String token){
        this.token = token;
        return Member.ofAthenticate(this);
    }
}
