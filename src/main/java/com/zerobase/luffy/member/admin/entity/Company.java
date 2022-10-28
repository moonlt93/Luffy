package com.zerobase.luffy.member.admin.entity;

import com.zerobase.luffy.common.base.BaseHeader;
import com.zerobase.luffy.member.bm.entity.BrandManager;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="company")
public class Company extends BaseHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="company_id")
    private Long id;



    private String companyName ;
    private String companyCall;
    private String companyResident;
    private String companyURL;
    private boolean companyEvent;
    private String companyStatus;


}
