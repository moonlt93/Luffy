package com.zerobase.luffy.member.bm.Dto;

import com.zerobase.luffy.member.admin.entity.Company;
import com.zerobase.luffy.member.bm.entity.BrandManager;
import com.zerobase.luffy.member.type.MemberCode;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BmDto{

    private Long id;
    private String username;

    private String managerName;
    private String password;
    private String managerCall;
    private String managerEmail;
    private String ROLE;
    private Company company;
    private String companyName;

    private MemberCode memberStatus;
    private String idList;


    public static BmDto EntityBuilder(BrandManager dto){
        return BmDto.builder()
                .id(dto.getId())
                .ROLE(dto.getROLE())
                .companyName(dto.getCompanyName())
                .memberStatus(dto.getMemberStatus())
                .managerEmail(dto.getManagerEmail())
                .managerName(dto.getManagerName())
                .managerCall(dto.getManagerCall())
                .build();

    }

}
