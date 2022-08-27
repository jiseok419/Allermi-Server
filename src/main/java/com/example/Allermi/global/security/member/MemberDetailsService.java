package com.example.Allermi.global.security.member;

import com.example.Allermi.domain.User.entity.Member;
import com.example.Allermi.domain.User.repository.MemberRepository;
import com.example.Allermi.global.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserid(email).orElseThrow(MemberNotFoundException::new);

        return MemberDetails.builder()
                .email(member.getUserid())
                .password(member.getPassword())
                .authorities(member.getRoles().stream()
                                        .map(auth -> new SimpleGrantedAuthority(auth.toString()))
                                        .collect(Collectors.toList()))
                .build();
    }
}
