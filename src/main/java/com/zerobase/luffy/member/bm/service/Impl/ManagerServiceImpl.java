package com.zerobase.luffy.member.bm.service.Impl;

import com.zerobase.luffy.Util.passUtil;
import com.zerobase.luffy.member.user.model.MessageResult;
import com.zerobase.luffy.member.admin.entity.Company;
import com.zerobase.luffy.member.admin.repository.CompanyRepository;
import com.zerobase.luffy.member.bm.Dto.BmDto;
import com.zerobase.luffy.member.bm.entity.BrandManager;
import com.zerobase.luffy.member.bm.repository.ManagerRepository;
import com.zerobase.luffy.member.bm.service.ManagerService;
import com.zerobase.luffy.member.type.MemberCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


        String encPassword = passUtil.encPassword(dto.getPassword());

        Optional<Company> optionalCompanyId = companyRepository.findByCompanyName(dto.getCompanyName());

        if (!optionalCompanyId.isPresent()) {
            return false;
        }

        Company company = optionalCompanyId.get();


        BrandManager manager = BrandManager.builder()
                .managerName(dto.getManagerName())
                .username(dto.getUsername())
                .password(encPassword)
                .managerEmail(dto.getManagerEmail())
                .managerCall(dto.getManagerCall())
                .ROLE("ROLE_MANAGER")
                .memberStatus(MemberCode.ING)
                .companyName(dto.getCompanyName())
                .company(company)
                .build();

        managerRepository.save(manager);


        return true;
    }

    @Override
    public Page<BrandManager> getAllList(Pageable pageable) {

        Page<BrandManager> bm = managerRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), 10, Sort.by("id")));


        return bm;
    }


    @Override
    public BmDto managerDetail(String username) {

        Optional<BrandManager> optionalBmDto = managerRepository.findByUsername(username);
        if (!optionalBmDto.isEmpty()) {

            BrandManager manager = optionalBmDto.get();

            return BmDto.builder()
                    .id(manager.getId())
                    .company(manager.getCompany())
                    .managerName(manager.getManagerName())
                    .managerCall(manager.getManagerCall())
                    .managerEmail(manager.getManagerEmail())
                    .memberStatus(manager.getMemberStatus())
                    .ROLE(manager.getROLE())
                    .companyName(manager.getCompanyName())
                    .build();


        }

        return null;
    }
    @Override
    public BmDto getManager(Long id) {

        Optional<BrandManager> optionalBrandManager = managerRepository.findById(id);

        if(optionalBrandManager.isEmpty()){
            return null;
        }
        BrandManager bm = optionalBrandManager.get();

        return BmDto.EntityBuilder(bm);

    }


    @Override
    public MessageResult updateDetail(BmDto dto) {


        Optional<BrandManager> optionalBrandManager = managerRepository.findById(dto.getId());
        if (optionalBrandManager.isEmpty()) {
            return new MessageResult(false, "회원정보가 없습니다.");

        }
        BrandManager manager = optionalBrandManager.get();
        manager.setMemberStatus(dto.getMemberStatus());

        managerRepository.save(manager);

        return new MessageResult(true);

    }


    @Override
    public void memberDelete(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids
            ) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {

                }
                if (id > 0) {
                    managerRepository.deleteById(id);
                }

            }
        }

    }

}
