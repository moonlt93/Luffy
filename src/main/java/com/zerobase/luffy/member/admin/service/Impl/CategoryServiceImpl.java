package com.zerobase.luffy.member.admin.service.Impl;


import com.zerobase.luffy.member.admin.Dto.CategoryDto;
import com.zerobase.luffy.member.admin.entity.Category;
import com.zerobase.luffy.member.admin.repository.CategoryRepository;
import com.zerobase.luffy.member.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> selectList() {

        List<Category> categories = categoryRepository.findAll(getSortBySortValueDesc());

        return CategoryDto.of(categories);
    }

    @Override
    public boolean add(String categoryName) {

        Category category = Category.builder()
                .categoryName(categoryName)
                .usingYn(true)
                .sortValue(0)
                .build();
        categoryRepository.save(category);
        return false;
    }

    @Override
    public boolean del(Long id) {

        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(CategoryDto dto) {
        Optional<Category> optionalCategory = categoryRepository.findById(dto.getId());
       if(optionalCategory.isPresent()){
           Category category=optionalCategory.get();

           category.setCategoryName(dto.getCategoryName());
           category.setSortValue(dto.getSortValue());
           category.setUsingYn(dto.isUsingYn());
           categoryRepository.save(category);

       }
       return true;

    }





    private Sort getSortBySortValueDesc(){
        return  Sort.by(Sort.Direction.DESC,"sortValue");
    };



}
