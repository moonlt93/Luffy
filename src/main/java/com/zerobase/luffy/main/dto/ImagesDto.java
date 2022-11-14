package com.zerobase.luffy.main.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImagesDto {


    private Long id;
    private String productName;
    private String urlFileName;
    private Long productId;


}
