package com.zerobase.luffy.member.admin.repository;

import com.zerobase.luffy.member.admin.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Page<Company> findByCompanyNameContaining(String keyword, Pageable pageable);
}
