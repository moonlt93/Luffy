package com.zerobase.luffy.member.bm.entity;

import com.zerobase.luffy.common.base.BaseHeader;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ManagerProduct")
public class ManagerProduct extends BaseHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Manager_product_id")
    private Long id;

    private String productName ;
    private String categoryName;
    private int pnt;
    private String content;
    private Long price;

    private String fileName;
    private String urlFileName;
    private String productStatus;
    private LocalDateTime endDt;
    private String writer;


}
