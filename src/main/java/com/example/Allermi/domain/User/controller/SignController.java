package com.example.Allermi.domain.User.controller;

import com.example.Allermi.domain.User.dto.*;
import com.example.Allermi.domain.User.entity.Member;
import com.example.Allermi.domain.User.repository.MemberRepository;
import com.example.Allermi.domain.User.result.SingleResult;
import com.example.Allermi.domain.User.service.ResponseService;
import com.example.Allermi.domain.User.service.SignService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sign")
public class SignController {

    private final SignService signService;
    private final ResponseService responseService;

    private final AuthenticationManager authenticationManager;

    private final MemberRepository memberRepository;

    @ApiOperation(value = "회원가입", notes = "회원가입을 진행한다.")
    @PostMapping("/register")
    public SingleResult<MemberRegisterResponseDto> register(@RequestBody MemberRegisterRequestDto requestDto) {
        log.info("request = {}, {}", requestDto.getUserid(), requestDto.getPassword(), requestDto.getAllergy());
        MemberRegisterResponseDto responseDto = signService.registerMember(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    @ApiOperation(value = "로컬 로그인", notes = "로컬을 통해 로그인을 진행한다.")
    @PostMapping("/login")
    public SingleResult<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto requestDto) {
        MemberLoginResponseDto responseDto = signService.loginMember(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    @ApiOperation(value = "토큰 재발급", notes = "Refresh Token을 통해 토큰을 재발급받는다.")
    @PostMapping("/reissue")
    public SingleResult<TokenResponseDto> reIssue(@RequestBody TokenRequestDto tokenRequestDto) {
        TokenResponseDto responseDto = signService.reIssue(tokenRequestDto);
        return responseService.getSingleResult(responseDto);
    }

    @GetMapping
    public ResponseEntity<?> getMyInfo(Authentication authentication) {
        Member username = signService.findById(authentication.getName());
        return new ResponseEntity<>(username, HttpStatus.OK);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<Boolean> checkId(@PathVariable String userid) {
        return ResponseEntity.ok(signService.checkUesrId(userid));
    }

    @PutMapping("/{userid}")
    public Member updateMember(@PathVariable String userid, @RequestBody Member member) {
        return signService.updateMember(userid, member);
    }

    @DeleteMapping("/{userid}")
    public Map<String, Object> delete(@PathVariable("userid") String userid) {
        Map<String, Object> response = new HashMap<>();

        if(signService.delete(userid) > 0) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
            response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
        }

        return response;
    }
}