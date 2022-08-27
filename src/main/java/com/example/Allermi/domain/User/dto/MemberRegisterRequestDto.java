package com.example.Allermi.domain.User.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegisterRequestDto {
    private String userid;
    private String password;
    private List<String> allergy;
}
