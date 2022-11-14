package com.zerobase.luffy.member.admin.service.Impl;


import com.zerobase.luffy.member.admin.Dto.CategoryDto;
import com.zerobase.luffy.member.admin.entity.Category;
import com.zerobase.luffy.member.admin.repository.CategoryQueryDslRepository;
import com.zerobase.luffy.member.admin.repository.CategoryRepository;
import com.zerobase.luffy.member.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryQueryDslRepository categoryQueryDslRepository;

    @Override
    public List<CategoryDto> selectList() {

        List<Category> categories = categoryRepository.findAll(getSortByCategoryIdDesc());

        return CategoryDto.of(categories);
    }

    @Override
    public boolean add(CategoryDto dto) {

        final Category parent = categoryRepository.findById(dto.getParentId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Menu입니다."));

        Category category = Category.builder()
                .categoryName(dto.getCategoryName())
                .usingYn(true)
                .sortValue(0)
                .parent(parent)
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
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            category.setCategoryName(dto.getCategoryName());
            category.setSortValue(dto.getSortValue());
            category.setUsingYn(dto.isUsingYn());
            categoryRepository.save(category);

        }
        return true;

    }


    private Sort getSortByCategoryIdDesc() {
        return Sort.by(Sort.Direction.DESC, "categoryId");
    }

    ;


    @Override
    public boolean createCategory(CategoryDto dto) {

        final Category parent = categoryRepository.findById(dto.getParentId())
                .orElseThrow(() -> new IllegalArgumentException("이미 존재하는 메뉴 입니다."));

        final  Category category = Category.builder()
                .categoryName(dto.getCategoryName())
                .parent(parent)
                .children(new ArrayList<>())
                .usingYn(true)
                .sortValue(0)
                .build();

        final Category saved = categoryRepository.save(category);

        return true;


    }

    @Transactional
    public List<CategoryDto> getCategoryList() {
        final List<Category> all = categoryQueryDslRepository.findAllWithQuerydsl();
        return all.stream().map(CategoryDto::new).collect(Collectors.toList());
    }


}


