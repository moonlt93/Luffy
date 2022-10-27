package com.zerobase.luffy.member.admin.service.Impl;

import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.repository.ProductDetailRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductDetailRepository productDetailRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;




    @Test
    @DisplayName("제품유무 테스트")
    void productDetail(){
    //given
    ProductDetail detail = ProductDetail.builder()
            .productName("제품").build();
            detail.setId(12L);

     given(productDetailRepository.findById(anyLong()))
             .willReturn(Optional.of(detail));
    //when

     Optional<ProductDetail> optionalProductDetail = productDetailRepository.findById(23L);

    //then
        assertEquals(12L,optionalProductDetail.get().getId());

    }


    @Test
    @DisplayName("업데이트 제품")
    void updateProduct(){
    //given
    ProductDetail detail = ProductDetail.builder()
            .productName("제품1")
            .categoryName("모자")
            .price(5600L)
            .build();
    detail.setId(23L);
        given(productDetailRepository.findById(anyLong()))
                .willReturn(Optional.of(detail));
        ArgumentCaptor<ProductDetail> captor = ArgumentCaptor.forClass(ProductDetail.class);
    //when
        Optional<ProductDetail> optionalDetail =productDetailRepository.findById(23L);

        ProductDetail details =optionalDetail.get();
        details.setId(23L);
        details.setProductName("제품1");
        details.setPrice(3300L);
        productDetailRepository.save(details);
    //then
        verify(productDetailRepository,times(1)).save(captor.capture());
        assertEquals(details.getId(),detail.getId());
        assertEquals(details.getPrice(),3300L);


    }

    @Test
    @DisplayName("제거한 상품이 남아있는지")
    void withDraw() {

        given(productDetailRepository.findById(anyLong()))
                .willReturn(Optional.empty());


        //when
        productDetailRepository.deleteById(324L);

        Optional<ProductDetail> optionalProductDetail = productDetailRepository.findById(324L);

        //then
        assertTrue(optionalProductDetail.isEmpty());


    }



}