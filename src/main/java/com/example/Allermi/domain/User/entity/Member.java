package com.example.Allermi.domain.User.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String userid;
    private String password;

    private String refreshToken;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> allergy;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;


    @Builder
    public Member(String userid, String password, List<Role> roles, List<String> allergy) {
        this.userid = userid;
        this.password = password;
        this.allergy = allergy;
        this.roles = Collections.singletonList(Role.MEMBER);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void update(List<String> allergy){
        this.allergy = allergy;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
