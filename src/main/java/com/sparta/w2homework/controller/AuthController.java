package com.sparta.w2homework.controller;

import com.sparta.w2homework.dto.MemberRequestDto;
import com.sparta.w2homework.dto.MemberResponseDto;
import com.sparta.w2homework.dto.TokenDto;
import com.sparta.w2homework.dto.TokenRequestDto;
import com.sparta.w2homework.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@Valid @RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login") //MemberRequestDto 에는 사용자가 로그인 시도한 ID / PW String 이 존재
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue") //TokenRequestDto 에는 재발급을 위한 AccessToken / RefreshToken String 이 존재
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}