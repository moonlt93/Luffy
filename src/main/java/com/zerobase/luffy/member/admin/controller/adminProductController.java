package com.zerobase.luffy.member.admin.controller;


import com.zerobase.luffy.member.admin.Dto.ProductDto;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.service.CategoryService;
import com.zerobase.luffy.member.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
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
          ,@RequestParam(required = false) String keyword) {

        Page<ProductDetail> list = null;
        if (keyword == null  || "".equals(keyword)) {
            list = productService.getAllList(pageable);
        } else {
            list = productService.findByTitleContaining(keyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int totalNum = list.getSize();
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = totalNum / 10 + 1;


        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalNum", totalNum);


        return "/admin/product/list";
    }


    @GetMapping(value = {"/create", "/edit"})
    public String GetProductCreate(Model model, HttpServletRequest req, ProductDto dto) {


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
    public String addSubmit(Model model, ProductDto dto
            , HttpServletRequest req, MultipartFile file) {

        String saveFileName = "";
        String urlFileName = "";

        if (file != null) {

            String originalFileName = file.getOriginalFilename();
            System.out.println(originalFileName);

            String baseLocalPath = "C:\\zero\\Ultimate\\Luffy\\src\\main\\resources\\static\\files";
            String baseUrlPath = "/files";
            String[] arrFileName = getNewSaveFile(baseUrlPath, baseLocalPath, originalFileName);

            saveFileName = arrFileName[0];
            urlFileName = arrFileName[1];

            try {
                File newFile = new File(saveFileName);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));

            } catch (IOException e) {
                log.info("#############################");
                log.info(e.getMessage());
            }
        }

        dto.setFileName(saveFileName);
        dto.setUrlFileName(urlFileName);

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
            boolean result = productService.add(dto);


        }

        return "redirect:/admin/product/list";
    }

    private String[] getNewSaveFile(String baseUrlPath, String baseLocalPath, String OriginalName) {
        LocalDate now = LocalDate.now();

        String[] dirs = {
                String.format("%s/%d/", baseLocalPath, now.getYear()),
                String.format("%s/%d/%02d/", baseLocalPath, now.getYear(), now.getMonthValue()),
                String.format("%s/%d/%02d/%02d/", baseLocalPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth())};

        String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        for (String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                //디렉토리가 없으면 생성
                file.mkdir();
            }
        }
        String fileExtension = " ";
        if (OriginalName != null) {
            int dotPos = OriginalName.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = OriginalName.substring(dotPos + 1);
            }
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFilename = String.format("%s%s", dirs[2], uuid);
        String urlFilename = String.format("%s%s", urlDir, uuid);
        if (fileExtension.length() > 0) {
            newFilename += "." + fileExtension;
            urlFilename += "." + fileExtension;
        }

        return new String[]{newFilename, urlFilename};

    }


    @PostMapping("/delete")
    public String deleteSubmit(ProductDto dto) {


        boolean result = productService.del(dto.getIdList());


        return "redirect:/admin/product/list";
    }


}
