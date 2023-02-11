package com.member.api;

import com.member.domain.Member;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member API", description = "Member API")
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @GetMapping
    public Member member(){
        return new Member("sangmoon", "32", "남자");
    }
}
