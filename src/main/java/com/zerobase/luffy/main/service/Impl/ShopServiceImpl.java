package com.zerobase.luffy.main.service.Impl;

import com.zerobase.luffy.main.dto.ImagesDto;
import com.zerobase.luffy.main.dto.ShopDto;
import com.zerobase.luffy.main.repository.ShopRepository;
import com.zerobase.luffy.main.service.ShopService;
import com.zerobase.luffy.member.admin.entity.Photoes;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.repository.PhotoesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final PhotoesRepository photoesRepository;


    @Override
    public ShopDto getProductDetail(Long id) {

        Optional<ProductDetail> optionalProductDetail = Optional.ofNullable(shopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제 된 상품입니다.")));
        if (optionalProductDetail.isPresent()) {

            ProductDetail detail = optionalProductDetail.get();


            ShopDto shop = ShopDto.builder()
                    .id(detail.getId())
                    .productName(detail.getProductName())
                    .urlFileName(detail.getUrlFileName())
                    .pnt(detail.getPnt())
                    .price(detail.getPrice())
                    .content(detail.getContent())
                    .categoryName(detail.getCategoryName())
                    .companyName(detail.getCompanyName())
                    .build();

            return shop;
        }

        return null;
    }

    @Override
    public List<Photoes> getImages(Long id) {

        log.info("id값 확인:" + id);
          List<Photoes> images = photoesRepository.findByproductDetail_Id(id);

            return images;

    }
}
