package com.zerobase.luffy.member.admin.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {

    private Long id;

    private String companyName ;
    private String companyCall;
    private String companyResident;
    private String companyURL;
    private boolean companyEvent;
    private String companyStatus;

}
