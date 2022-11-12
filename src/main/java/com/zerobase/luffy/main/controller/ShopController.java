package com.zerobase.luffy.main.controller;


import com.zerobase.luffy.member.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/show")
public class ShopController {

    private final CategoryService categoryService;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getList() throws Exception {
            return ResponseEntity.ok(categoryService.getCategoryList());

    }
}
