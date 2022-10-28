package com.zerobase.luffy.member.admin.controller;


import com.zerobase.luffy.Util.FileUtil;
import com.zerobase.luffy.member.admin.Dto.ProductDto;
import com.zerobase.luffy.member.admin.Dto.ProductFileDto;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.service.CategoryService;
import com.zerobase.luffy.member.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
@Slf4j
public class adminProductController {

    private final CategoryService categoryService;

    private final ProductService productService;


    @GetMapping("/list")
    public String GetProduct(Model model,
                             @PageableDefault(page = 0, size = 10,
                                     sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , @RequestParam(required = false) String keyword) {

        Page<ProductDetail> list = null;
        if (keyword == null || "".equals(keyword)) {
            list = productService.getAllList(pageable);
        } else {
            list = productService.findByTitleContaining(keyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int totalNum = list.getTotalPages();
        int startPage = totalNum < 5 ? 1 : totalNum - 4;
        System.out.println(totalNum);

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("totalNum", totalNum);
        model.addAttribute("startPage", startPage);

        return "/admin/product/list";
    }


    @GetMapping(value = {"/create", "/edit"})
    public String GetProductCreate(Model model, HttpServletRequest req, ProductDto dto
                        ) {

        model.addAttribute("category", categoryService.selectList());

        boolean isEdit = req.getRequestURI().contains("/edit");
        ProductDto dto2 = new ProductDto();
        if (isEdit) {
            long id = dto.getId();
            ProductDto existProduct = productService.getById(id);
            if (existProduct == null) {
                model.addAttribute("message", "상품정보가 없습니다.");
                return "/common/error/error";
            }
            dto2 = existProduct;
        }

        model.addAttribute("isEdit", isEdit);
        model.addAttribute("detail", dto2);

        return "/admin/product/create";
    }


    @PostMapping(value = {"/create", "/edit"})
    public String addSubmit(Model model, ProductDto dto, @ModelAttribute ProductFileDto fileDto
            , HttpServletRequest req, Authentication authentication) throws Exception {
        if (fileDto == null) {
            throw new Exception("전달받은 데이터가 없음. ");
        }
        log.info("list ={}", fileDto.getItemImgList());

        List<MultipartFile> fileList = fileDto.getItemImgList();

        for (MultipartFile file : fileList) {

            FileUtil fileutil = new FileUtil();
            fileutil.imageMaker(file, dto);

            /*list<MultipartFile> 하면 여러개 한번에 받기 가능*/
            boolean isEdit = req.getRequestURI().contains("/edit");

            if (isEdit) {
                long id = dto.getId();
                ProductDto existProduct = productService.getById(id);

                if (existProduct == null) {
                    model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                    return "common/error/error";
                }

                boolean result = productService.set(dto);

            } else {
                String writer = authentication.getName();
                dto.setWriter(writer);
                System.out.println(dto.getCategoryName());
                boolean result = productService.add(dto);

            }
        }


        return "redirect:/admin/product/list";
    }


    @PostMapping("/delete")
    public String deleteSubmit(ProductDto dto) {


        boolean result = productService.del(dto.getIdList());


        return "redirect:/admin/product/list";
    }


}
