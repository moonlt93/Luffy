package com.zerobase.luffy.member.bm.Dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BmProductDto  {

    private Long id;
    private String categoryName;
    private String imgLink;
    private String content;
    private Long price;
    private String productName;
    private String companyName;
    private Long companyId;

    private int pnt;
    private int fileCount;
    private String productStatus;
    private LocalDateTime regDt;
    private LocalDateTime upDt;
    private String fileName;
    private String urlFileName;
    private String idList;
    private String searchKeyword;
    private String writer;



}
