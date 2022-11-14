package com.zerobase.luffy.main.controller;

import com.zerobase.luffy.main.entity.Coupon;
import com.zerobase.luffy.main.service.PaymentService;
import com.zerobase.luffy.member.user.dto.MemberDto;
import com.zerobase.luffy.member.user.entity.Member;
import com.zerobase.luffy.member.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    private final MemberService memberService;
    private final PaymentService paymentService;

    @GetMapping("/create")
    public String GetPayment(Model model, Principal principal){
        log.info("결제 페이지");
        MemberDto memberDetail = memberService.memberDetails(principal.getName());

        List<Coupon> coupon = paymentService.getCoupons(memberDetail.getId());

        model.addAttribute("coupon",coupon);
        model.addAttribute("member",memberDetail);

        return "/paymentSys/create";
    }
}
