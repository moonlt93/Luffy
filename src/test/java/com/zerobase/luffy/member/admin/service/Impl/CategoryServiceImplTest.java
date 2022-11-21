package com.zerobase.luffy.member.admin.service.Impl;

import com.zerobase.luffy.member.admin.Dto.CategoryDto;
import com.zerobase.luffy.member.admin.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {



    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;



    @Test
    @DisplayName("ParentId가 없을때 error 반환 확인")
   void createCategory_IdNull(){

        //given
        CategoryDto dto = CategoryDto.builder()
                        .categoryName("test")
                        .id(1L)
                        .sortValue(0).build();

    //when
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> categoryServiceImpl.createCategory(dto));


    //then
        assertEquals("해당하는 parentId가 없습니다.",exception.getMessage());
    }

    @Test
    @DisplayName("카테고리 삭제시 해당하는 id 카테고리를 지우는지 test")
    void category_del(){
    //given
    given(categoryRepository.findById(anyLong()))
            .willReturn(Optional.empty());
    //when
        NullPointerException exception= assertThrows(NullPointerException.class,
                () -> categoryServiceImpl.del(12L));
    //then
        assertEquals("키에 해당하는 카테고리가 없습니다.",exception.getMessage());

    }



}