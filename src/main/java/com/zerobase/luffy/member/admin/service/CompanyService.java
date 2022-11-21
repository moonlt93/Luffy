package com.zerobase.luffy.member.admin.service;

import com.zerobase.luffy.member.admin.Dto.CompanyDto;
import com.zerobase.luffy.member.admin.entity.Company;
import com.zerobase.luffy.response.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {
    CompanyDto getById(long id);

    ResponseMessage add(CompanyDto dto);

    ResponseMessage set(CompanyDto dto);

    Page<Company> getAllList(Pageable pageable);

    Page<Company> findByTitleContaining(String keyword, Pageable pageable);

    ResponseMessage del(String idList);


    List<Company> selectAllList();
}
