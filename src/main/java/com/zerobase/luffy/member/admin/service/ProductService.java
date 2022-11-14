package com.zerobase.luffy.member.admin.service;

import com.zerobase.luffy.member.admin.Dto.ProductDto;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {


    boolean set(ProductDto dto);

    boolean add(ProductDto dto);


    Page<ProductDetail> getAllList(Pageable pageable);

    ProductDto getById(long id);

    boolean del(String idList);

    Page<ProductDetail> findByTitleContaining(String searchKeyword, Pageable pageable);

    ProductDetail findById(Long productId);
}