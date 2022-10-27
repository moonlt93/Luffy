package com.zerobase.luffy.member.admin.controller;

import com.zerobase.luffy.member.admin.Dto.MemberInput;
import com.zerobase.luffy.member.admin.service.AdminService;
import com.zerobase.luffy.common.dto.MemberDto;
import com.zerobase.luffy.common.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class adminController {

  private final AdminService adminService;
  private final MemberService memberService;

    @GetMapping("/main")
    public String GetAdmin(){
        return "/admin/main";
    }


    @GetMapping("/member")
    public String GetMember(MemberDto dto, Model model ){

    List<MemberDto> list = memberService.memberList(dto);

        int totalCount  = list.size();
        model.addAttribute("list",list);
        model.addAttribute("totalCount",totalCount);
        return "/admin/member";
    }


    @PostMapping("/member/delete")
    public String PostMemberDel(MemberInput dto){

        adminService.memberDelete(dto.getIdList());

        log.info("삭제완료");
        return "redirect:/admin/member";
    }

}
