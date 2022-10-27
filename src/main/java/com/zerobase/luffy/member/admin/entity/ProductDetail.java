package com.zerobase.luffy.member.admin.entity;

import com.zerobase.luffy.member.admin.base.ProductHeader;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail extends ProductHeader {


    private String productName ;
    private String categoryName;
    int pnt;
    private String content;
    private Long price;

    private String fileName;
    private String urlFileName;
    private String productStatus;
    private LocalDateTime endDt;



}
