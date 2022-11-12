package com.zerobase.luffy.member.admin.Dto;


import com.zerobase.luffy.member.admin.entity.Category;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Long id ;
    private String categoryName;
    private int sortValue;
    private boolean usingYn;
    private int courseCount;
    private Long parentId;
    private List<CategoryDto> children;

    public CategoryDto(final Category category) {
       this.id = category.getCategoryId();
       this.categoryName=category.getCategoryName();
       this.sortValue = category.getSortValue();
       this.children=category.getChildren().stream().map(CategoryDto:: new ).collect(Collectors.toList());
    }


    public static List<CategoryDto> of(List<Category> categories){

        if(categories != null){
            List<CategoryDto> categoryList = new ArrayList<>();
            for(Category x: categories){
                categoryList.add(of(x));
            }
            return categoryList;
        }
        return null;
    }

    private static CategoryDto of(Category category){
        return CategoryDto.builder()
                .id(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .sortValue(category.getSortValue())
                .usingYn(category.isUsingYn())
                .build();
    }



}
