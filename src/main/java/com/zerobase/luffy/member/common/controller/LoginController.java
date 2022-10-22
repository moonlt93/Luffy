package com.zerobase.luffy.member.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class LoginController {


    @RequestMapping("/login")
    public String login(){

        return"/member/loginForm";
    }


}
