package com.team15.fourcuts.domain.post.controller;

import com.team15.fourcuts.domain.post.dto.LoginRequestDto;
import com.team15.fourcuts.domain.post.dto.MessageResponseDto;
import com.team15.fourcuts.domain.post.dto.SignupRequestDto;
import com.team15.fourcuts.domain.post.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j //로깅 기능
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    //회원 가입 구현
    @PostMapping("/signup")
    public MessageResponseDto signup(@RequestBody @Valid SignupRequestDto requestDto){
        userService.signup(requestDto);

        return new MessageResponseDto("회원가입 성공");
    }

    //로그인 구현
    @PostMapping("/login")
    public MessageResponseDto login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        userService.login(requestDto, res);

        return new MessageResponseDto("로그인 성공");
    }
}
