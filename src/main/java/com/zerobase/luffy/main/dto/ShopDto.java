package com.zerobase.luffy.main.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShopDto {
    // item정보 가져오기

    private Long id;
    private String content;
    private Long price;
    private String productName;
    private String categoryName;
    private String companyName;

    private int pnt;
    private String urlFileName;
    private String searchKeyword;
    private int fileCount;



}
