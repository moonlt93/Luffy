package com.zerobase.luffy.main.service;

import com.zerobase.luffy.main.dto.ImagesDto;
import com.zerobase.luffy.main.dto.ShopDto;
import com.zerobase.luffy.member.admin.entity.Photoes;

import java.util.List;

public interface ShopService {
    ShopDto getProductDetail(Long id);

    List<Photoes> getImages(Long id);


    //  List<ShopDto> getAllItem(ShopDto dto);
}
