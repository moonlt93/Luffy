package com.zerobase.luffy.member.bm.service.Impl;

import com.zerobase.luffy.member.bm.Dto.BmProductDto;
import com.zerobase.luffy.member.bm.entity.ManagerProduct;
import com.zerobase.luffy.member.bm.repository.ManagerProRepository;
import com.zerobase.luffy.member.bm.service.ManagerProService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

        if(optionalDto.isPresent()){

            ManagerProduct dto = optionalDto.get();

            return BmProductDto.builder()
                    .id(dto.getId())
                    .fileName(dto.getFileName())
                    .productStatus(dto.getProductStatus())
                    .productName(dto.getProductName())
                    .categoryName(dto.getCategoryName())
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
    public boolean add(BmProductDto dto) {

        ManagerProduct pro = ManagerProduct.builder()
                .categoryName(dto.getCategoryName())
                .price(dto.getPrice())
                .pnt(dto.getPnt())
                .content(dto.getContent())
                .urlFileName(dto.getUrlFileName())
                .writer(dto.getWriter())
                .fileName(dto.getFileName())
                .productName(dto.getProductName())
                .productStatus(dto.getProductStatus())
                .build();
        managerProRepository.save(pro);

        return true;
    }


    @Override
    public Page<ManagerProduct> getAllList(Pageable pageable) {



        return managerProRepository.findAll(PageRequest.of(pageable.getPageNumber(),10,
                Sort.by(Sort.Order.desc("id"))));
    }

    @Override
    public Page<ManagerProduct> findByTitleContaining(String keyword, Pageable pageable) {

       Page<ManagerProduct> page = managerProRepository.findByProductNameContaining(keyword,pageable);

        return page;
    }

    @Override
    public boolean set(BmProductDto dto) {
        Optional<ManagerProduct> optionalDetail =managerProRepository.findById(dto.getId());

        if(!optionalDetail.isPresent() ){
            return false;
        }

        ManagerProduct detail = optionalDetail.get();

        detail.setCategoryName(dto.getCategoryName());
        detail.setPrice(dto.getPrice());
        detail.setContent(dto.getContent());
        detail.setPnt(dto.getPnt());
        detail.setProductName(dto.getProductName());
        detail.setFileName(dto.getFileName());
        detail.setUpDt(LocalDateTime.now());
        detail.setEndDt(LocalDateTime.now());
        detail.setWriter(dto.getWriter());
        detail.setUrlFileName(dto.getUrlFileName());
        managerProRepository.save(detail);

        return true;
    }
}
