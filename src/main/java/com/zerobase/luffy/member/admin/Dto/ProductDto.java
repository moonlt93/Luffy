package com.zerobase.luffy.member.admin.Dto;


import com.zerobase.luffy.member.admin.entity.ProductDetail;
import com.zerobase.luffy.member.admin.entity.ProductHeader;
import com.zerobase.luffy.member.type.ProductCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class ProductDto {

    Long id;

    String imgLink;
    String content;
    String productSize;
    Long price;
    String productName;

    LocalDateTime productRegDt;
    LocalDateTime UpDt;
    LocalDateTime EndDt;
    int pnt;
    ProductCode memberStatus;
    ProductHeader header;


}
