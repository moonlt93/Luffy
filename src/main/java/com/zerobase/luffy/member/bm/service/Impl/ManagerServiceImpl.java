package com.zerobase.luffy.member.bm.service.Impl;

import com.zerobase.luffy.Util.passUtil;
import com.zerobase.luffy.member.admin.entity.Company;
import com.zerobase.luffy.member.admin.repository.CompanyRepository;
import com.zerobase.luffy.member.bm.Dto.BmDto;
import com.zerobase.luffy.member.bm.entity.BrandManager;
import com.zerobase.luffy.member.bm.repository.ManagerRepository;
import com.zerobase.luffy.member.bm.service.ManagerService;
import com.zerobase.luffy.member.type.MemberCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final CompanyRepository companyRepository;

    @Override
    public boolean register(BmDto dto) {


       String encPassword= passUtil.encPassword(dto.getPassword());

       Optional<Company> optionalCompanyId= companyRepository.findByCompanyName(dto.getCompanyName());

       if(!optionalCompanyId.isPresent()){
           return false;
       }

       Company company = optionalCompanyId.get();


       BrandManager manager =BrandManager.builder()
               .managerName(dto.getManagerName())
               .username(dto.getUsername())
               .password(encPassword)
               .managerEmail(dto.getManagerEmail())
               .managerCall(dto.getManagerCall())
               .ROLE("ROLE_MANAGER")
               .memberStatus(MemberCode.ING)
               .companyName(dto.getManagerName())
               .company(company)
               .build();

      managerRepository.save(manager);


        return true;
    }







}
