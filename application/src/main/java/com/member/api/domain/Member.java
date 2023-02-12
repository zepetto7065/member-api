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
    public boolean isCheckedPhoneNumber;

    private static Member of(Member member){
        return new Member(member.getMemberId(), member.email, member.nickname, member.password, member.name, member.phoneNumber, true);
    }

    public Member checkPassword(String targetPassword) {
        if(!targetPassword.equals(this.getPassword())){
            throw new LoginException("비밀번호가 일치하지 않습니다.");
        }
        return Member.of(this);
    }

    public Member applyPassword(String modifiedPassword) {
        if(!isCheckedPhoneNumber) throw new MemberException("전화번호 인증이 되지 않았습니다.");
        this.password = modifiedPassword;
        return Member.of(this);
    }
}
