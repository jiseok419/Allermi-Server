package com.example.Allermi.domain.User.service;


import com.example.Allermi.domain.User.dto.*;
import com.example.Allermi.domain.User.entity.Member;
import com.example.Allermi.domain.User.repository.MemberRepository;
import com.example.Allermi.global.exception.InvalidRefreshTokenException;
import com.example.Allermi.global.exception.LoginFailureException;
import com.example.Allermi.global.exception.MemberEmailAlreadyExistsException;
import com.example.Allermi.global.exception.MemberNotFoundException;
import com.example.Allermi.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Dto로 들어온 값을 통해 회원가입을 진행
     */
    @Transactional
    public MemberRegisterResponseDto registerMember(MemberRegisterRequestDto requestDto) {
        validateDuplicated(requestDto.getUserid());
        Member member = memberRepository.save(
                Member.builder()
                        .userid(requestDto.getUserid())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .allergy(requestDto.getAllergy())
                        .build());

        return MemberRegisterResponseDto.builder()
                .id(member.getId())
                .userid(member.getUserid())
                .build();
    }

    /**
     * Unique한 값을 가져야하나, 중복된 값을 가질 경우를 검증
     */
    public void validateDuplicated(String userid) {
        if (memberRepository.findByUserid(userid).isPresent())
            throw new MemberEmailAlreadyExistsException();
    }

    @Transactional
    public MemberLoginResponseDto loginMember(MemberLoginRequestDto requestDto) {
        Member member = memberRepository.findByUserid(requestDto.getUserid()).orElseThrow(LoginFailureException::new);
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword()))
            throw new LoginFailureException();
        member.updateRefreshToken(jwtTokenProvider.createRefreshToken());
        return new MemberLoginResponseDto(member.getId(), jwtTokenProvider.createToken(requestDto.getUserid()), member.getRefreshToken());
    }

    //토큰 재발행
    @Transactional
    public TokenResponseDto reIssue(TokenRequestDto requestDto) {
        if (!jwtTokenProvider.validateTokenExpiration(requestDto.getRefreshToken()))
            throw new InvalidRefreshTokenException();

        Member member = findMemberByToken(requestDto);

        if (!member.getRefreshToken().equals(requestDto.getRefreshToken()))
            throw new InvalidRefreshTokenException();

        String accessToken = jwtTokenProvider.createToken(member.getUserid());
        String refreshToken = jwtTokenProvider.createRefreshToken();
        member.updateRefreshToken(refreshToken);
        return new TokenResponseDto(accessToken, refreshToken);
    }

    public Member findMemberByToken(TokenRequestDto requestDto) {
        Authentication auth = jwtTokenProvider.getAuthentication(requestDto.getAccessToken());
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();
        return memberRepository.findByUserid(username).orElseThrow(MemberNotFoundException::new);
    }

    public Member findById(String Userid) {
        return memberRepository.findMemberByUserid(Userid);
    }

    public boolean checkUesrId(String userid){
        return memberRepository.existsByUserid(userid);
    }

    @Transactional
    public Member updateMember(String userid, Member member) {
        Member memberData = memberRepository.findByUserid(userid).orElseThrow(IllegalArgumentException::new);
        memberData.update(member.getAllergy());
        return memberData;
    }

    @Transactional
    public int delete(String userid) {
        Optional<Member> oUser = memberRepository.findByUserid(userid);
        if(oUser.isPresent()) {
            memberRepository.delete(oUser.get());
            return 1;
        }
        return 0;
    }
}