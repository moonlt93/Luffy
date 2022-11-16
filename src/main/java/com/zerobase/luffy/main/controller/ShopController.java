package com.zerobase.luffy.main.controller;


import com.zerobase.luffy.main.dto.ShopDto;
import com.zerobase.luffy.main.service.ShopService;
import com.zerobase.luffy.member.admin.entity.Photoes;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.service.ProductService;
import com.zerobase.luffy.member.type.ProductCode;
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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    private final ProductService productService;
    private final ShopService shopService;

    @GetMapping("/list")
    public String MainAllList(Model model, @PageableDefault(page = 0, size = 10,
            sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

       // Page<ProductDetail> items = productService.getAllList(pageable);
        ProductCode str = ProductCode.SellOK;
        Page<ProductDetail> items = productService.getUseList(str,pageable);
        model.addAttribute("items",items);

        return "/shop/list";
    }


    @GetMapping(value="/{productId}")
    public String shopDetail(Model model, @PathVariable("productId") Long id){
        ShopDto dto = shopService.getProductDetail(id);

      ArrayList<Photoes> images =new ArrayList<>(shopService.getImages(id));
        model.addAttribute("items",dto);
        model.addAttribute("images",images);

        return"/shop/detail";
    }

    @GetMapping("/fin")
    public String shopFin(){
        log.info("주문성공페이지");
        return"/shop/fin";
    }


}
