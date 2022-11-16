package com.zerobase.luffy.main.controller;


import com.zerobase.luffy.main.dto.ShopDto;
import com.zerobase.luffy.main.dto.WishDto;
import com.zerobase.luffy.main.service.ShopService;
import com.zerobase.luffy.main.service.WishService;
import com.zerobase.luffy.member.admin.entity.Photoes;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.service.ProductService;
import com.zerobase.luffy.member.type.ProductCode;
import com.zerobase.luffy.member.user.dto.PaymentDto;
import com.zerobase.luffy.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransientPropertyValueException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationNotSupportedException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    private final ProductService productService;
    private final ShopService shopService;
    private final WishService wishService;

    @GetMapping("/list")
    public String MainAllList(Model model, @PageableDefault(page = 0, size = 10,
            sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        // Page<ProductDetail> items = productService.getAllList(pageable);
        ProductCode str = ProductCode.SellOK;
        Page<ProductDetail> items = productService.getUseList(str, pageable);
        model.addAttribute("items", items);

        return "/shop/list";
    }


    @GetMapping(value = "/{productId}")
    public String shopDetail(Model model, @PathVariable("productId") Long id) {
        ShopDto dto = shopService.getProductDetail(id);

        ArrayList<Photoes> images = new ArrayList<>(shopService.getImages(id));
        model.addAttribute("items", dto);
        model.addAttribute("images", images);

        return "/shop/detail";
    }

    @GetMapping("/fin")
    public String shopFin() {
        log.info("주문성공페이지");
        return "/shop/fin";
    }

    @PostMapping("/wish")
    @ResponseBody
    public ResponseEntity makeWishList(@RequestBody WishDto dto
            , Principal principal) {


        try {

            ResponseMessage message = wishService.createWish(dto, principal.getName());

            return new ResponseEntity(AnswerMaker(String.valueOf(message)), HttpStatus.OK);

        } catch (NullPointerException e) {

            return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }


    }

    private Map<String, Object> AnswerMaker(String words) {
        Map<String, Object> map = new HashMap<>();
        map.put(words, words);

        return map;
    }

}
