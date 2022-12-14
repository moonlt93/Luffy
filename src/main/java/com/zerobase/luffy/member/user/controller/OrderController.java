package com.zerobase.luffy.member.user.controller;

import com.zerobase.luffy.member.user.dto.OrderDto;
import com.zerobase.luffy.member.user.dto.OrderListDto;
import com.zerobase.luffy.member.user.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity createOrders(@RequestBody OrderDto dto , Principal principal
            , HttpServletRequest req){


        Object OrderId = null;
        HashMap<String,Object> map = new HashMap<>();
        try{
            dto.setUsername(principal.getName());
            OrderId = orderService.createOrder(dto);
            log.info("주문번호 생성 "+OrderId);
            map.put("code",OrderId);

        }catch(NullPointerException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(map ,HttpStatus.OK);

    }

    @PostMapping("/delete")
    public String deleteOrders( OrderListDto dto ){



           orderService.deleteOrders(dto);


        return "redirect:/member/myPackage";

    }






}
