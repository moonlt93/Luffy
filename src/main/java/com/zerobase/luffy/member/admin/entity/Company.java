package com.zerobase.luffy.member.admin.entity;

import com.zerobase.luffy.Util.base.BaseHeader;
import lombok.*;

import javax.persistence.*;

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
