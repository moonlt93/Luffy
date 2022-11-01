package com.zerobase.luffy.member.admin.controller;

import com.zerobase.luffy.member.bm.Dto.BmDto;
import com.zerobase.luffy.member.bm.entity.BrandManager;
import com.zerobase.luffy.member.bm.service.ManagerService;
import com.zerobase.luffy.member.type.MemberCode;
import com.zerobase.luffy.member.user.model.MessageResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/manager")
public class adminBrandManagerController {


    private final ManagerService managerService;

    @GetMapping("/list")
    public String getManagerList(Model model, @PageableDefault(page = 0, size = 10,
            sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        Page<BrandManager> list =managerService.getAllList(pageable);


        int nowPage = list.getPageable().getPageNumber() + 1;
        int totalNum = list.getTotalPages();
        int startPage = totalNum < 5 ? 1 : totalNum - 4;
        System.out.println(totalNum);

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("totalNum", totalNum);
        model.addAttribute("startPage", startPage);

        return "/admin/manager/list";

    }



    @GetMapping("/update")
    public String GetUpdate(Model model, BmDto dto){


            BmDto list = managerService.getManager(dto.getId());
            if(list == null){
                model.addAttribute("message","불러올 list가 없습니다.");
                return"/common/error/error";
        }


        model.addAttribute("list",list);
        return "/admin/manager/info";

    }



    @PostMapping("/update")
    public String ModifyInfo(Model model, BmDto dto){


        MessageResult result = managerService.updateDetail(dto);

        if(!result.isResult()){
            model.addAttribute("message",result.getMessage());
            return "/common/error/error";
        }
        return "redirect:/admin/manager/list";

    }

    @PostMapping("/delete")
    public String PostMemberDel(BmDto dto){

        managerService.memberDelete(dto.getIdList());

        log.info("삭제완료");
        return "redirect:/admin/manager/list";
    }




}
