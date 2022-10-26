package com.zerobase.luffy.member.admin.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/product")
@RestController
@Slf4j
public class adminProductController {


    @GetMapping("/list")
    public String GetProduct(){

        return"/admin/product/list";
    }


    @GetMapping("/create")
    public String GetProductCreate(){

        return"/admin/product/create";
    }


}
