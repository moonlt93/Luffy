package com.zerobase.luffy.member.bm.entity;

import com.zerobase.luffy.common.base.BaseHeader;
import com.zerobase.luffy.member.admin.entity.Company;
import com.zerobase.luffy.member.type.MemberCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manager")
public class BrandManager extends BaseHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "manager_id")
    private Long id;



    private String companyName;
    private String username;
    private String managerName;
    private String password;
    private String managerCall;
    private String managerEmail;
    private String ROLE;

    @Enumerated(EnumType.STRING)
    private MemberCode memberStatus;





}
