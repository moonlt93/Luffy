package com.zerobase.luffy.member.user.controller;

import com.zerobase.luffy.main.entity.Coupon;
import com.zerobase.luffy.member.user.dto.MemberDto;
import com.zerobase.luffy.member.user.dto.OrderDto;
import com.zerobase.luffy.member.user.dto.PaymentDto;
import com.zerobase.luffy.member.user.service.MemberService;
import com.zerobase.luffy.member.user.service.OrderService;
import com.zerobase.luffy.member.user.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    private final MemberService memberService;
    private final PaymentService paymentService;
    private final OrderService orderService;

    //결재 페이지 이동.
    @GetMapping("/create/{code}")
    public String GetPayment(Model model, Principal principal, @PathVariable String code){
        log.info("결제 페이지");
        log.info("해당 ordercode "+code);
        MemberDto memberDetail = memberService.memberDetails(principal.getName());

        List<Coupon> coupon = paymentService.getCoupons(memberDetail.getId());

        OrderDto item = orderService.findByOrderId(code);


        model.addAttribute("coupon",coupon);
        model.addAttribute("member",memberDetail);
        model.addAttribute("code",code);
        model.addAttribute("item",item);


        return "/paymentSys/create";
    }

    //결제 내역 생성
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity createPayment(@RequestBody PaymentDto dto, Principal principal) throws InterruptedException {

        log.info("dto값 확인:"+dto.toString());
        dto.setUsername(principal.getName());
        PaymentDto dtos = paymentService.addPayment(dto);

        return new ResponseEntity(dtos, HttpStatus.OK);
    }
}
