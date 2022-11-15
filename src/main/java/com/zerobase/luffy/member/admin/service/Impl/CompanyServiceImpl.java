package com.zerobase.luffy.member.admin.service.Impl;

import com.zerobase.luffy.member.admin.Dto.CompanyDto;
import com.zerobase.luffy.member.admin.entity.Company;
import com.zerobase.luffy.member.admin.repository.CompanyRepository;
import com.zerobase.luffy.member.admin.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;


    @Override
    public List<Company> selectAllList() {
        return companyRepository.findAll();
    }

    @Override
    public Page<Company> getAllList(Pageable pageable) {

        return companyRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), 10, Sort.by("id")));

    }

    @Override
    public Page<Company> findByTitleContaining(String keyword, Pageable pageable) {

        Page<Company> page = companyRepository.findByCompanyNameContaining(keyword, pageable);

        return page;
    }

    @Override
    public CompanyDto getById(long id) {

        Optional<Company> optionalDto = companyRepository.findById(id);

        if (!optionalDto.isEmpty()) {

            Company company = optionalDto.get();


            return CompanyDto.builder()
                    .id(company.getId())
                    .companyEvent(company.isCompanyEvent())
                    .companyName(company.getCompanyName())
                    .companyResident(company.getCompanyResident())
                    .companyURL(company.getCompanyURL())
                    .companyCall(company.getCompanyCall())
                    .companyStatus(company.getCompanyStatus())
                    .build();


        }
        return null;
    }


    @Override
    public boolean add(CompanyDto dto) {

        Company company = Company.builder()
                .companyName(dto.getCompanyName())
                .companyEvent(dto.isCompanyEvent())
                .companyResident(dto.getCompanyResident())
                .companyURL(dto.getCompanyURL())
                .companyStatus(dto.getCompanyStatus())
                .companyCall(dto.getCompanyCall())
                .build();
        companyRepository.save(company);
        return true;
    }


    @Override
    public boolean set(CompanyDto dto) {
        Optional<Company> optionalCompany = companyRepository.findById(dto.getId());

        if (optionalCompany.isEmpty()) {
            return false;
        }

        Company company = optionalCompany.get();
        company.setCompanyName(dto.getCompanyName());
        company.setCompanyEvent(dto.isCompanyEvent());
        company.setCompanyResident(dto.getCompanyResident());
        company.setCompanyURL(dto.getCompanyURL());
        company.setCompanyStatus(dto.getCompanyStatus());
        company.setCompanyCall(dto.getCompanyCall());
        company.setId(dto.getId());

        companyRepository.save(company);

        return true;
    }


    @Override
    public boolean del(String idList) {


        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");

            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (id > 0) {
                    companyRepository.deleteById(id);
                }
            }

        }
        return true;
    }


}
