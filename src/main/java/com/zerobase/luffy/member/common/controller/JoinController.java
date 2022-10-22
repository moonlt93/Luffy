package com.zerobase.luffy.member.common.controller;

import com.zerobase.luffy.member.common.dto.MemberDto;
import com.zerobase.luffy.member.common.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/join")
@RequiredArgsConstructor
@Slf4j
public class JoinController {


    private final MemberService memberService;


    @GetMapping("/register")
    public String getRegister() {

        log.info("회원가입");
        return "/join/register";
    }

    @PostMapping("/register")
    public String postRegister(Model model, MemberDto param, HttpServletRequest req) {


        param.setIp(req.getRemoteAddr());

        boolean result = memberService.register(param);

        if (!result) {
           log.info("문제가 생겼습니다.");
        }

        return "index";
    }
}
