package com.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
    private String name;
    private String age;
    private String gender;
}
