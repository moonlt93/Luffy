package com.zerobase.luffy.member.admin.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductFileDto {

    private String fileName;
    private List<MultipartFile> itemImgList;
}
