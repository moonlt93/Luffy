package com.zerobase.luffy.member.admin.controller;


import com.zerobase.luffy.member.admin.Dto.CategoryDto;
import com.zerobase.luffy.member.admin.service.CategoryService;
import com.zerobase.luffy.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/category")
public class adminCategoryController {


    private final CategoryService categoryService;

    @GetMapping("/list")
    public String Categorylist(Model model) {

        List<CategoryDto> list = categoryService.selectList();

        model.addAttribute("list", list);
        return "/admin/category/list";
    }

    @PostMapping("/create")
    public String add(CategoryDto dto) {


        categoryService.createCategory(dto);

        return "redirect:/admin/category/list";
    }

    @PostMapping("/delete")
    public String del(CategoryDto dto) {

        categoryService.del(dto.getId());

        return "redirect:/admin/category/list";
    }

    @PostMapping("/update")
    public String update(CategoryDto dto) {

        ResponseMessage result = categoryService.update(dto);

        log.info("category Update result"+result);

        return "redirect:/admin/category/list";
    }

}
