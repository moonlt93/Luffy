package com.zerobase.luffy.member.bm.service.Impl;

import com.zerobase.luffy.Exception.ManagerCode.ManagerProException;
import com.zerobase.luffy.member.bm.Dto.BmProductDto;
import com.zerobase.luffy.member.bm.entity.ManagerProduct;
import com.zerobase.luffy.member.bm.entity.Photo;
import com.zerobase.luffy.member.bm.repository.ManagerProRepository;
import com.zerobase.luffy.member.bm.service.ManagerProService;
import com.zerobase.luffy.member.type.ProductCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ManagerProServiceImpl implements ManagerProService {


    private final ManagerProRepository managerProRepository;


    @Override
    public BmProductDto getById(long id) {

        Optional<ManagerProduct> optionalDto = managerProRepository.findById(id);


        if (optionalDto.isPresent()) {

            ManagerProduct dto = optionalDto.get();


            return BmProductDto.builder()
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
    public ManagerProException add(BmProductDto dto) {

        String fileName = dto.getFileName();
        String UrlName = dto.getUrlFileName();
        int len = dto.getFileCount();


        if (len > 1) {
            String fileName2 = dto.getFileName();
            String UrlName2 = dto.getUrlFileName();

            String[] fileNamePeek = fileName2.split("-");
            String[] urlNamePeek = UrlName2.split("-");

            ManagerProduct pro = ManagerProduct.builder()
                    .categoryName(dto.getCategoryName())
                    .price(dto.getPrice())
                    .pnt(dto.getPnt())
                    .fileName(fileNamePeek[0])
                    .urlFileName(urlNamePeek[0])
                    .content(dto.getContent())
                    .writer(dto.getWriter())
                    .productName(dto.getProductName())
                    .productStatus(String.valueOf(ProductCode.Processing))
                    .photoList(new ArrayList<>())
                    .build();


            for (int i = 1; i < len; i++) {
                Photo photo = Photo.builder()
                        .urlFileName(urlNamePeek[i])
                        .fileName(fileNamePeek[i])
                        .writer(dto.getWriter())
                        .ManagerProduct(pro)
                        .productName(pro.getProductName())
                        .build();

                pro.addPhoto(photo);

            }


            managerProRepository.save(pro);
            return new ManagerProException("등록에 성공하였습니다.");

        } else {
            String replaceFile = replaceString(fileName);
            String replaceUrl = replaceString(UrlName);

            ManagerProduct pro = ManagerProduct.builder()
                    .categoryName(dto.getCategoryName())
                    .price(dto.getPrice())
                    .pnt(dto.getPnt())
                    .content(dto.getContent())
                    .urlFileName(replaceUrl)
                    .writer(dto.getWriter())
                    .fileName(replaceFile)
                    .productName(dto.getProductName())
                    .productStatus(String.valueOf(ProductCode.Processing))
                    .photoList(new ArrayList<>())
                    .build();


            Photo photo = Photo.builder()
                    .urlFileName(dto.getUrlFileName())
                    .fileName(dto.getFileName())
                    .writer(dto.getWriter())
                    .ManagerProduct(pro)
                    .productName(pro.getProductName())
                    .build();

            pro.getPhotoList().add(photo);

            managerProRepository.save(pro);
            return new ManagerProException("등록에 성공하였습니다.");
        }

    }


    @Override
    public Page<ManagerProduct> getAllList(Pageable pageable) {


        return managerProRepository.findAll(PageRequest.of(pageable.getPageNumber(), 10,
                Sort.by(Sort.Order.desc("id"))));
    }

    @Override
    public Page<ManagerProduct> findByTitleContaining(String keyword, Pageable pageable) {

        Page<ManagerProduct> page = managerProRepository.findByProductNameContaining(keyword, pageable);

        return page;
    }

    @Override
    public boolean set(BmProductDto dto) {
        Optional<ManagerProduct> optionalDetail = managerProRepository.findById(dto.getId());

        if (!optionalDetail.isPresent()) {
            return false;
        }

        ManagerProduct detail = optionalDetail.get();

        String fileName = dto.getFileName();
        String UrlName = dto.getUrlFileName();
        int len = dto.getFileCount();


        if (len > 1) {
            String fileName2 = dto.getFileName();
            String UrlName2 = dto.getUrlFileName();

            String[] fileNamePeek = fileName2.split("-");
            String[] urlNamePeek = UrlName2.split("-");

            ManagerProduct pro = ManagerProduct.builder()
                    .id(dto.getId())
                    .categoryName(dto.getCategoryName())
                    .price(dto.getPrice())
                    .pnt(dto.getPnt())
                    .fileName(fileNamePeek[0])
                    .urlFileName(urlNamePeek[0])
                    .content(dto.getContent())
                    .writer(dto.getWriter())
                    .productName(dto.getProductName())
                    .productStatus(String.valueOf(ProductCode.Processing))
                    .photoList(new ArrayList<>())
                    .build();


            for (int i = 1; i < len; i++) {
                Photo photo = Photo.builder()
                        .urlFileName(urlNamePeek[i])
                        .fileName(fileNamePeek[i])
                        .writer(dto.getWriter())
                        .ManagerProduct(pro)
                        .build();

                pro.addPhoto(photo);

            }


            managerProRepository.save(pro);
            return true;

        } else {
            String replaceFile = replaceString(fileName);
            String replaceUrl = replaceString(UrlName);

            ManagerProduct pro = ManagerProduct.builder()
                    .id(dto.getId())
                    .categoryName(dto.getCategoryName())
                    .price(dto.getPrice())
                    .pnt(dto.getPnt())
                    .content(dto.getContent())
                    .urlFileName(replaceUrl)
                    .writer(dto.getWriter())
                    .fileName(replaceFile)
                    .productName(dto.getProductName())
                    .productStatus(String.valueOf(ProductCode.Processing))
                    .photoList(new ArrayList<>())
                    .build();


            Photo photo = Photo.builder()
                    .urlFileName(dto.getUrlFileName())
                    .fileName(dto.getFileName())
                    .writer(dto.getWriter())
                    .ManagerProduct(pro)
                    .build();

            pro.getPhotoList().add(photo);

            managerProRepository.save(pro);
            return true;
        }



    }


    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            log.info("IdList구분: " + idList);
            String[] ids = idList.split(",");

            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);

                    managerProRepository.deleteById(id);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
        return true;
    }


    private String replaceString(String replaceFile){

        return replaceFile.replace("-", "");
    }
}
