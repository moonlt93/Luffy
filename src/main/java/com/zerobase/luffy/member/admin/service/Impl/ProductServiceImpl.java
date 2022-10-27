package com.zerobase.luffy.member.admin.service.Impl;

import com.zerobase.luffy.member.admin.Dto.ProductDto;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.repository.ProductDetailRepository;
import com.zerobase.luffy.member.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductDetail> getAllList() {

        List<ProductDetail> list = productDetailRepository.findAll(getSortBySortValueDesc());

        return list;

    }

    private Sort getSortBySortValueDesc(){
        return  Sort.by(Sort.Direction.DESC,"id");
    };


    @Override
    public ProductDto getById(long id) {

        Optional<ProductDetail> optionalDto = productDetailRepository.findById(id);

        if(!optionalDto.isEmpty()){

            ProductDetail dto =optionalDto.get();

            return ProductDto.builder()
                    .id(dto.getId())
                    .fileName(dto.getFileName())
                    .productStatus(dto.getProductStatus())
                    .productName(dto.getProductName())
                    .categoryName(dto.getCategoryName())
                    .price(dto.getPrice())
                    .urlFileName(dto.getUrlFileName())
                    .content(dto.getContent())
                    .pnt(dto.getPnt())
                    .build();
        }

        return null;
    }



    @Override
    public boolean set(ProductDto dto) {

        Optional<ProductDetail> optionalDetail =productDetailRepository.findById(dto.getId());

        if(!optionalDetail.isPresent() ){
            return false;
        }

        ProductDetail detail = optionalDetail.get();
        detail.setCategoryName(dto.getCategoryName());
        detail.setPrice(dto.getPrice());
        detail.setContent(dto.getContent());
        detail.setPnt(dto.getPnt());
        detail.setProductName(dto.getProductName());
        detail.setFileName(dto.getFileName());
        detail.setUpDt(LocalDateTime.now());
        detail.setEndDt(LocalDateTime.now());
        detail.setUrlFileName(dto.getUrlFileName());
        productDetailRepository.save(detail);

        return true;
    }

    @Override
    public boolean add(ProductDto dto) {


        ProductDetail detail = ProductDetail.builder()
                .categoryName(dto.getCategoryName())
                .productStatus(dto.getProductStatus())
                .productName(dto.getProductName())
                .content(dto.getContent())
                .price(dto.getPrice())
                .productName(dto.getProductName())
                .pnt(dto.getPnt())
                .productStatus(dto.getProductStatus())
                .fileName(dto.getFileName())
                .urlFileName(dto.getUrlFileName())

                .build();
        productDetailRepository.save(detail);

        return true;
    }


    @Override
    public boolean del(String idList) {

        if(idList != null && idList.length()>0){
            String [] ids = idList.split(",");

            for(String x : ids){
                long id = 0L;
                try{
                    id=Long.parseLong(x);

                }catch(Exception e){
                    e.printStackTrace();
                }
                if(id>0){
                    productDetailRepository.deleteById(id);
                }
            }
        }
        return true;
    }
}
