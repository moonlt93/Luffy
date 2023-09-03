package com.zerobase.luffy.main.controller;

import com.zerobase.luffy.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {



    @GetMapping({"/", " "})
    public String GetMain() {


        log.info("mainController" );


        return "index";
    }

    @GetMapping("/test/login")
    @ResponseBody
    public String testLogin(Authentication authentication,@AuthenticationPrincipal UserDetails userDetails){
        System.out.println("// test // login======================");
        PrincipalDetails principalDetails =(PrincipalDetails) authentication.getPrincipal();
        System.out.println("[하하]:"+ principalDetails.getUsername());
        System.out.println("[User details]"+userDetails.getUsername());
        return "세션 정보 확인";
    }


}
