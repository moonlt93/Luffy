package com.zerobase.luffy.main.dto;

import com.zerobase.luffy.member.user.entity.Member;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class WishDto {

    private Long WishId;

    private Member member;

    private String productName;
    private String fileUrl;

    private String productUrl;
}
