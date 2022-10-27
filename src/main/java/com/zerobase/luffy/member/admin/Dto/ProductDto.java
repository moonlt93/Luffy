package com.zerobase.luffy.member.admin.Dto;


import com.zerobase.luffy.member.admin.base.ProductHeader;
import com.zerobase.luffy.member.admin.entity.ProductDetail;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto  {


    private Long id;
    private String categoryName;
    private String imgLink;
    private String content;
    private Long price;
    private String productName;

    private int pnt;
    private String productStatus;
    private LocalDateTime upDt;
    private String fileName;
    private String urlFileName;
    private String idList;
    private String  searchKeyword;
    public static ProductDto entityBuilder(ProductDetail detail) {

        return ProductDto.builder()
                .id(detail.getId())
                .productName(detail.getProductName())
                .categoryName(detail.getCategoryName())
                .price(detail.getPrice())
                .productStatus(detail.getProductStatus())
                .content(detail.getContent())
                .pnt(detail.getPnt())
                .urlFileName(detail.getUrlFileName())
                .fileName(detail.getFileName())
                .build();
    }
}
