package com.zerobase.luffy.member.admin.service.Impl;


import com.zerobase.luffy.member.admin.Dto.CategoryDto;
import com.zerobase.luffy.member.admin.entity.Category;
import com.zerobase.luffy.member.admin.repository.CategoryRepository;
import com.zerobase.luffy.member.admin.service.CategoryService;
import com.zerobase.luffy.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> selectList() {

        List<Category> categories = categoryRepository.findAll(getSortByCategoryIdDesc());

        return CategoryDto.of(categories);
    }



    @Override
    public void del(Long id) {

        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("키에 해당하는 카테고리가 없습니다.")));

        if(optionalCategory.isPresent()) {

            Category category = optionalCategory.get();

            categoryRepository.deleteCategory(category.getCategoryId());
            log.info("category Del is success");
        }
        log.error("category del is fail ");
    }

    @Override
    public ResponseMessage update(CategoryDto dto) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new NullPointerException("id에 해당하는 Category가 없습니다.")));

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            category.setCategoryName(dto.getCategoryName());
            category.setSortValue(dto.getSortValue());
            category.setUsingYn(dto.isUsingYn());
            categoryRepository.save(category);
            return ResponseMessage.success;
        }

        return ResponseMessage.fail;
    }


    private Sort getSortByCategoryIdDesc() {
        return Sort.by(Sort.Direction.DESC, "categoryId");
    }




    @Override
    public void createCategory(CategoryDto dto)   {

        //해당 id와 이름이 같지 않은 카테고리 생성
         Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findById(dto.getParentId())
                 .orElseThrow(() -> new NoSuchElementException("해당하는 parentId가 없습니다.")));


         if(optionalCategory.isPresent()) {

             Category cate = optionalCategory.get();

             final Category category = Category.builder()
                     .categoryName(dto.getCategoryName())
                     .parent(cate)
                     .children(new ArrayList<>())
                     .usingYn(true)
                     .sortValue(0)
                     .build();

             categoryRepository.save(category);

            log.info("category is success");
         }

        log.error("Create category is fail");

    }


}


