package com.example.Allermi.domain.User.dto;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRegisterResponseDto {
    @ApiParam(value = "로그인 PK", required = true)
    private Long id;
    @ApiParam(value = "로그인 아이디", required = true)
    private String userid;


    @Builder
    public MemberRegisterResponseDto(Long id, String userid) {
        this.id = id;
        this.userid = userid;
    }
}
