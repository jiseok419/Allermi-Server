package com.example.Allermi.domain.User.dto;

import com.example.Allermi.domain.User.entity.Member;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class MemberResponseDto implements Serializable {
    private Long id;
    private String password;
    private List<String> allergy;

    public MemberResponseDto(Member member){
        this.id = member.getId();
        this.password = member.getPassword();
        this.allergy = member.getAllergy();
    }
}
