package com.zerobase.luffy.member.admin.controller;

import com.zerobase.luffy.member.admin.Dto.CompanyDto;
import com.zerobase.luffy.member.admin.Dto.ProductDto;
import com.zerobase.luffy.member.admin.entity.Company;
import com.zerobase.luffy.member.admin.service.CompanyService;
import com.zerobase.luffy.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/company")
public class adminCompanyController {


    private final CompanyService companyService;


    @GetMapping("/list")
    public String GetCompany(Model model,
                             @PageableDefault(page = 0, size = 10,sort = "id"
                                     , direction = Sort.Direction.DESC) Pageable pageable
                                    , @RequestParam(required = false) String keyword) {

        Page<Company> company = null;

        if (keyword == null || "".equals(keyword)) {
            company = companyService.getAllList(pageable);
        } else {
            company = companyService.findByTitleContaining(keyword, pageable);
        }

        int nowPage = company.getPageable().getPageNumber()+1 ;
        int totalNum = company.getTotalPages();
        int startPage = totalNum < 5 ? 1 : totalNum - 4;


        model.addAttribute("company", company);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("totalNum", totalNum);
        model.addAttribute("startPage", startPage);

        return "/admin/company/list";
    }


    @GetMapping(value = {"/create", "/edit"})
    public String getCompanyCreate(Model model, HttpServletRequest req, CompanyDto dto) {

        boolean isEdit = req.getRequestURI().contains("/edit");

        CompanyDto dto2 = new CompanyDto();
        if (isEdit) {
            long id = dto.getId();
            CompanyDto existProduct = companyService.getById(id);
            if (existProduct == null) {
                model.addAttribute("message", "상품정보가 없습니다.");
                return "/common/error/error";
            }
            dto2 = existProduct;
        }
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("detail", dto2);

        return "/admin/company/create";
    }


    @PostMapping(value = {"/create", "/edit"})
    public String addSubmit(Model model, CompanyDto dto
            , HttpServletRequest req) throws Exception {

        boolean isEdit = req.getRequestURI().contains("/edit");

        System.out.println(dto);
        if (isEdit) {

            long id = dto.getId();
            CompanyDto existProduct = companyService.getById(id);

            if (existProduct == null) {
                model.addAttribute("message", "상품정보가 없습니다.");
                return "/common/error/error";
            }
            ResponseMessage result = companyService.set(dto);
            log.info("company Update info:"+ result);

        } else {
            ResponseMessage result = companyService.add(dto);

            log.info("company create info:"+ result);

        }

        return "redirect:/admin/company/list";
    }

    @PostMapping("/delete")
    public String deleteSubmit(ProductDto dto) {

        ResponseMessage result = companyService.del(dto.getIdList());

        log.info("company delete info:"+ result);


        return "redirect:/admin/company/list";
    }


}



