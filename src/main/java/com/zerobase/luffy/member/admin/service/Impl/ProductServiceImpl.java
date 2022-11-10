package com.zerobase.luffy.member.admin.service.Impl;

import com.zerobase.luffy.member.admin.Dto.ProductDto;
import com.zerobase.luffy.member.admin.entity.Photoes;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.repository.ProductDetailRepository;
import com.zerobase.luffy.member.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductDetailRepository productDetailRepository;

    @Override
    public Page<ProductDetail> getAllList(Pageable pageable) {


        return  productDetailRepository.findAll(PageRequest.of(pageable.getPageNumber(), 10, Sort.by(Sort.Order.desc("id"))));


    }



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
                    .regDt(dto.getRegDt())
                    .price(dto.getPrice())
                    .urlFileName(dto.getUrlFileName())
                    .content(dto.getContent())
                    .pnt(dto.getPnt())
                    .writer(dto.getWriter())
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
        detail.setEndDt(LocalDateTime.now());
        detail.setWriter(dto.getWriter());
        detail.setUrlFileName(dto.getUrlFileName());
        productDetailRepository.save(detail);

        return true;
    }

    @Override
    public boolean add(ProductDto dto) {

        String fileName = dto.getFileName();
        String UrlName = dto.getUrlFileName();
        int len = dto.getFileCount();


        if (len > 1) {
            String fileName2 = dto.getFileName();
            String UrlName2 = dto.getUrlFileName();

            String[] fileNamePeek = fileName2.split("-");
            String[] urlNamePeek = UrlName2.split("-");

            ProductDetail pro = ProductDetail.builder()
                    .categoryName(dto.getCategoryName())
                    .price(dto.getPrice())
                    .pnt(dto.getPnt())
                    .fileName(fileNamePeek[0])
                    .urlFileName(urlNamePeek[0])
                    .content(dto.getContent())
                    .writer(dto.getWriter())
                    .productName(dto.getProductName())
                    .productStatus(dto.getProductStatus())
                    .photoes(new ArrayList<>())
                    .build();

            for (int i = 1; i < len; i++) {
                Photoes photoes = Photoes.builder()
                        .urlFileName(urlNamePeek[i])
                        .fileName(fileNamePeek[i])
                        .writer(dto.getWriter())
                        .productDetail(pro)
                        .build();

                pro.addPhotoes(photoes);
            }
            productDetailRepository.save(pro);
            return true;
        }else {

            String replaceFile = replaceString(fileName);
            String replaceUrl = replaceString(UrlName);

            ProductDetail pro = ProductDetail.builder()
                    .categoryName(dto.getCategoryName())
                    .price(dto.getPrice())
                    .pnt(dto.getPnt())
                    .content(dto.getContent())
                    .urlFileName(replaceUrl)
                    .writer(dto.getWriter())
                    .fileName(replaceFile)
                    .productName(dto.getProductName())
                    .productStatus(dto.getProductStatus())
                    .photoes(new ArrayList<>())
                    .build();


            Photoes photoes = Photoes.builder()
                    .urlFileName(dto.getUrlFileName())
                    .fileName(dto.getFileName())
                    .writer(dto.getWriter())
                    .productDetail(pro)
                    .build();

            pro.getPhotoes().add(photoes);
            productDetailRepository.save(pro);
             return true;
        }

    }

    @Override
    public boolean del(String idList) {

        if(idList != null && idList.length()>0){
            String [] ids = idList.split(",");

            for(String x : ids){
                long id = 0L;
                try{
                    id=Long.parseLong(x);
                    if(ids.length>0){

                    productDetailRepository.deleteById(id);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }
        return true;
    }


    @Override
    public Page<ProductDetail> findByTitleContaining(String searchKeyword, Pageable pageable) {

        Page<ProductDetail> page =  productDetailRepository.findByProductNameContaining(searchKeyword,pageable);


        return page;
    }


    private String replaceString(String replaceFile){

        return replaceFile.replace("-", "");
    }
}
