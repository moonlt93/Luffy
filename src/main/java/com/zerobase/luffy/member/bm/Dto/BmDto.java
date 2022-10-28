package com.zerobase.luffy.member.bm.Dto;

import com.zerobase.luffy.member.admin.entity.Company;
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

    private MemberCode memberStatus;



}
