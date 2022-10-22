package com.zerobase.luffy.member.bm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class controller {

    @GetMapping("/main")
    public String getManager(){

    return "/manager/main";
    }
}
