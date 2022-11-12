package com.zerobase.luffy.main.controller;

import com.zerobase.luffy.member.admin.Dto.CategoryDto;
import com.zerobase.luffy.member.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

   /*  private  final CategoryService categoryService;*/


    @GetMapping({"/", " "})
    public String GetMain() {


        log.info("mainController");

       // model.addAttribute("categories",menu);

        return "index";
    }


}
