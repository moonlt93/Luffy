package com.zerobase.luffy.common.controller;

import com.zerobase.luffy.common.dto.MemberDto;
import com.zerobase.luffy.common.model.MessageResult;
import com.zerobase.luffy.common.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {


    private final MemberService memberService;


    @RequestMapping("/login")
    public String login(){

        return"/member/loginForm";
    }

    /* 회원가입 이동*/
    @GetMapping("/register")
    public String getRegister() {

        log.info("회원가입");
        return "/member/register";
    }

    /*회원 가입*/
    @PostMapping("/register")
    public String postRegister(MemberDto param, HttpServletRequest req) {


        param.setIp(req.getRemoteAddr());

        boolean result = memberService.register(param);

        if (!result) {
            log.info("문제가 생겼습니다.");
        }

        return "index";
    }

    /*마이 페이지*/
    @GetMapping("/myPage")
    public String GetMyPage(Principal principal, Model model){

        String username= principal.getName();

         MemberDto list = memberService.memberDetails(username);

         model.addAttribute("list",list);

        return "/member/myPage";
    };

    @PostMapping("/myPage")
    public String ModifyMyPage(Model model,MemberDto dto,HttpServletRequest req){

        Long id = Long.valueOf(req.getParameter("userNo"));
        dto.setId(id);



        MessageResult result = memberService.updateDetail(dto);

        if(!result.isResult()){
            model.addAttribute("message",result.getMessage());
            return "/common/error/error";
        }
        return "redirect:/member/myPage";

    }



    @GetMapping("/mywithdraw/{id}")
    public String GetMyWithDraw(@PathVariable Long id,Model model){

        model.addAttribute("id",id);

        return "/member/passCheck";
    }
    @PostMapping("/mywithdraw")
    public String myWithDraw(MemberDto dto, Model model
    ,HttpServletRequest req){

        String password=req.getParameter("password");

        dto.setPassword(password);

        Long id = Long.valueOf(req.getParameter("id"));

        MessageResult result = memberService.withDraw(id,dto.getPassword());

        if(!result.isResult()){
            model.addAttribute("message",result.getMessage());
            return"/common/error/error";
        }
        return"redirect:/member/logout";


    }



}

/*@GetMapping("/myPage")
    public String GetMyPage(Principal principal, Model model){

        String username= principal.getName();

         List<MemberInfo> list = memberService.getUserListByUsername(username)
                .stream().map(memberDto ->MemberInfo.builder()
                         .id(memberDto.getId())
                        .regDt(memberDto.getRegDt())
                        .ROLE(memberDto.getROLE())
                        .registration(memberDto.getRegistration())
                        .phone(memberDto.getPhone())
                        .userName(memberDto.getUserName())
                        .email(memberDto.getEmail())
                        .name(memberDto.getName())
                        .build())
                        .collect(Collectors.toList());

         model.addAttribute("list",list);

        return "/member/myPage";
    };*/
