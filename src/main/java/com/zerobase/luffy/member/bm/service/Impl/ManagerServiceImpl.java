package com.zerobase.luffy.member.bm.service.Impl;

import com.zerobase.luffy.Util.passUtil;
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

       BrandManager manager =BrandManager.builder()
               .managerName(dto.getManagerName())
               .username(dto.getUsername())
               .password(encPassword)
               .managerEmail(dto.getManagerEmail())
               .managerCall(dto.getManagerCall())
               .ROLE("ROLE_MANAGER")
               .memberStatus(MemberCode.ING)
               .companyName(dto.getManagerName())

               .build();

      vaildateDuplicateManager(manager);
      managerRepository.save(manager);


        return true;
    }

    private void vaildateDuplicateManager(BrandManager manager) {
        List<BrandManager> findMembers =
                managerRepository.findByName(manager.getManagerName());
        if(!findMembers.isEmpty()){
           throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }





}
