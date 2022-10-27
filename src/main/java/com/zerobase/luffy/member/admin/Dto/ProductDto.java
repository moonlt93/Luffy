package com.zerobase.luffy.member.admin.Dto;


import com.zerobase.luffy.member.admin.base.ProductHeader;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    Long id;
    String categoryName;
    String imgLink;
    String content;
    Long price;
    String productName;

    LocalDateTime productRegDt;
    LocalDateTime UpDt;
    LocalDateTime EndDt;
    int pnt;
    ProductHeader header;
    String productStatus;

    String fileName;
    String urlFileName;
    String idList;

}
