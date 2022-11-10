package com.zerobase.luffy.member.admin.service;

import com.zerobase.luffy.member.admin.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> selectList();

    boolean add(String categoryName);

    boolean del(Long id);

    boolean update(CategoryDto dto);


}
