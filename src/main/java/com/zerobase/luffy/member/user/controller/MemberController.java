package com.zerobase.luffy.member.user.controller;

import com.zerobase.luffy.main.dto.WishDto;
import com.zerobase.luffy.main.entity.Wish;
import com.zerobase.luffy.main.service.WishService;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.service.ProductService;
import com.zerobase.luffy.member.user.dto.MemberDto;
import com.zerobase.luffy.member.user.entity.OrderItem;
import com.zerobase.luffy.member.user.model.MessageResult;
import com.zerobase.luffy.member.user.service.MemberService;
import com.zerobase.luffy.member.bm.service.ManagerService;
import com.zerobase.luffy.member.user.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {


    private final MemberService memberService;

    private final OrderService orderService;
    private final ProductService productService;

    private final WishService wishService;


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

        List<Wish> dto = wishService.getWishes(list.getId());

         model.addAttribute("wishes",dto);
         model.addAttribute("list",list);

        return "/member/myPage";
    }

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
    @GetMapping("/myPackage")
    public String memberPackage(Model model,Principal principal
     ,@PageableDefault(page = 0, size = 5,
             sort = "orderId", direction = Sort.Direction.DESC) Pageable pageable){

        Page<OrderItem> items = orderService.getOrderList(pageable,principal.getName());


        int nowPage = items.getPageable().getPageNumber() + 1;
        int totalNum = items.getTotalPages();
        int startPage = totalNum < 5 ? 1 : totalNum - 4;

        log.info("내 장바구니");
        model.addAttribute("item",items);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("totalNum", totalNum);
        model.addAttribute("startPage", startPage);

        return "/member/myPackage";
    }
    @PostMapping("/wishDelete")
    public String deleteWishes(WishDto dto){

        String id = dto.getIdList();

        wishService.deleteWishes(id);

        return "redirect:/member/myPage";
    }



}

