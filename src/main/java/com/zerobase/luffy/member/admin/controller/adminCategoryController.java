package com.zerobase.luffy.member.admin.controller;


import com.zerobase.luffy.member.admin.Dto.CategoryDto;
import com.zerobase.luffy.member.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
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


        boolean result = categoryService.createCategory(dto);

        System.out.println("parentId: "+dto.getParentId());
     // boolean result = categoryService.add(dto);


        return "redirect:/admin/category/list";
    }

    @PostMapping("/delete")
    public String del(CategoryDto dto) {

        boolean result = categoryService.del(dto.getId());
        return "redirect:/admin/category/list";
    }

    @PostMapping("/update")
    public String update(CategoryDto dto) {
        System.out.println(dto.isUsingYn());

        boolean result = categoryService.update(dto);
        return "redirect:/admin/category/list";
    }

}
