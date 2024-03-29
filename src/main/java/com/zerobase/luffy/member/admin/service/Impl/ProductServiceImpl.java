package com.zerobase.luffy.member.admin.service.Impl;

import com.zerobase.luffy.member.admin.Dto.ProductDto;
import com.zerobase.luffy.member.admin.entity.Photoes;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.repository.ProductDetailRepository;
import com.zerobase.luffy.member.admin.service.ProductService;
import com.zerobase.luffy.member.type.ProductCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductDetailRepository productDetailRepository;


    @Override
    public Page<ProductDetail> getAllList(Pageable pageable) {


        return productDetailRepository.findAll(PageRequest.of(pageable.getPageNumber(), 10, Sort.by(Sort.Order.desc("id"))));


    }


    @Override
    public ProductDto getById(long id) {

        Optional<ProductDetail> optionalDto = productDetailRepository.findById(id);

        if (optionalDto.isPresent()) {

            ProductDetail dto = optionalDto.get();

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
        log.error("list get fail");
        return null;
    }


    @Override
    public boolean set(ProductDto dto) {

        Optional<ProductDetail> optionalDetail = Optional.ofNullable(productDetailRepository.findById(dto.getId())
                .orElseThrow(() -> new NullPointerException("해당하는 제품이 없습니다.")));

        if (optionalDetail.isEmpty()) {
            return false;
        }

        ProductDetail detail = optionalDetail.get();

        String fileName = dto.getFileName();
        String UrlName = dto.getUrlFileName();
        int len = dto.getFileCount();


        if (len > 1) {
            String fileName2 = dto.getFileName();
            String UrlName2 = dto.getUrlFileName();

            String[] fileNamePeek = fileName2.split("-");
            String[] urlNamePeek = UrlName2.split("-");

            detail.setCategoryName(dto.getCategoryName());
            detail.setPrice(dto.getPrice());
            detail.setContent(dto.getContent());
            detail.setPnt(dto.getPnt());
            detail.setProductName(dto.getProductName());
            detail.setFileName(fileNamePeek[0]);
            detail.setWriter(dto.getWriter());
            detail.setUrlFileName(urlNamePeek[0]);
            detail.setPhotoes(new ArrayList<>());
            detail.setProductStatus(dto.getProductStatus());

            for (int i = 1; i < len; i++) {
                Photoes photoes = Photoes.builder()
                        .urlFileName(urlNamePeek[i])
                        .fileName(fileNamePeek[i])
                        .writer(dto.getWriter())
                        .productDetail(detail)
                        .build();

                detail.addPhotoes(photoes);
            }

            productDetailRepository.save(detail);

            return true;
        } else {

            String replaceFile = replaceString(fileName);
            String replaceUrl = replaceString(UrlName);

            detail.setCategoryName(dto.getCategoryName());
            detail.setPrice(dto.getPrice());
            detail.setContent(dto.getContent());
            detail.setPnt(dto.getPnt());
            detail.setProductName(dto.getProductName());
            detail.setFileName(replaceFile);
            detail.setWriter(dto.getWriter());
            detail.setUrlFileName(replaceUrl);
            detail.setPhotoes(new ArrayList<>());
            detail.setProductStatus(dto.getProductStatus());
            productDetailRepository.save(detail);
            return true;
        }

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
            log.info("add success");
            return true;
        } else {

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
            log.info("add success");
            return true;
        }

    }

    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");

            for (String x : ids) {
                long id = 0L;
                try {
                        id = Long.parseLong(x);
                        productDetailRepository.deleteById(id);
                    log.info("add success");
                } catch (Exception e) {
                    log.error("add fail");
                }

            }
        }
        return true;
    }


    @Override
    public Page<ProductDetail> findByTitleContaining(String searchKeyword, Pageable pageable) {

        Page<ProductDetail> page = productDetailRepository.findByProductNameContaining(searchKeyword, pageable);


        return page;
    }

    @Override
    public Page<ProductDetail> getUseList(ProductCode str, Pageable pageable) {

        Page<ProductDetail> page =productDetailRepository.findByProductStatusContaining(str,pageable);
        return page;
    }

    @Override
    public Page<ProductDetail> getCategoryList(ProductCode str, Long childrenId, Pageable pageable) {
        Page<ProductDetail> page = productDetailRepository. findByProductStatusContainingAndCategory_CategoryId(pageable,str,childrenId);

        return page;
    }

    private String replaceString(String replaceFile) {

        return replaceFile.replace("-", "");
    }

    //배너 사진 1개만 가져오기
    @Override
    public ProductDetail findById(Long productId) {

        Optional<ProductDetail> optionalProductDetail = Optional.ofNullable(productDetailRepository.findById(productId)
                .orElseThrow(() -> new NullPointerException("해당하는 상품이 없습니다.")));

        if (optionalProductDetail.isPresent()) {
            ProductDetail pro = optionalProductDetail.get();

            return ProductDetail.builder()
                    .urlFileName(pro.getUrlFileName())
                    .build();
        }
        log.error("get fail");
        return null;
    }


}
