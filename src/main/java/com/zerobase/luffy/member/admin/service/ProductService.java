package com.zerobase.luffy.member.admin.service;

import com.zerobase.luffy.member.admin.Dto.ProductDto;
import com.zerobase.luffy.member.admin.entity.ProductDetail;

import java.util.List;

public interface ProductService {



    boolean set(ProductDto dto);

    boolean add(ProductDto dto);


    List<ProductDetail> getAllList();

    ProductDto getById(long id);

    boolean del(String idList);
}
