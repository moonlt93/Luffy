package com.zerobase.luffy.member.admin.service;

import com.zerobase.luffy.member.admin.Dto.CategoryDto;
import com.zerobase.luffy.response.ResponseMessage;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> selectList();

    void del(Long id);

    ResponseMessage update(CategoryDto dto);


    void createCategory(CategoryDto dto);


}
