package com.zerobase.luffy.member.bm.service;

import com.zerobase.luffy.member.bm.Dto.BmProductDto;
import com.zerobase.luffy.member.bm.entity.ManagerProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerProService {


    BmProductDto getById(long id);


    boolean add(BmProductDto dto);


    Page<ManagerProduct> getAllList(Pageable pageable);

    Page<ManagerProduct> findByTitleContaining(String keyword, Pageable pageable);


    boolean set(BmProductDto dto);
}
