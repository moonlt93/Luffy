package com.zerobase.luffy.member.bm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/manager")
public class ManagerController {



    @GetMapping("/main")
    public String getMain(){
        return "/manager/main";
    }


}
