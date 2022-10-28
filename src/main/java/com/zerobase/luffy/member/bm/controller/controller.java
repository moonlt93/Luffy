package com.zerobase.luffy.member.bm.controller;

import com.zerobase.luffy.member.bm.Dto.BmDto;
import com.zerobase.luffy.member.bm.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class controller {

    private final ManagerService managerService;

    @GetMapping("/main")
    public String getManager(){

    return "/manager/main";
    }


    @GetMapping("/register")
    public String getregister(){

        return "/manager/register";
    }

    @PostMapping("/register")
    public String postRegister(BmDto dto ){



        boolean result =managerService.register(dto);

        return"/manager/main";
    }


}
