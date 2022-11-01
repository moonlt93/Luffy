package com.zerobase.luffy.member.bm.service;

import com.zerobase.luffy.member.bm.Dto.BmDto;
import com.zerobase.luffy.member.bm.entity.BrandManager;
import com.zerobase.luffy.member.user.model.MessageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerService {

    boolean register(BmDto dto);

    Page<BrandManager> getAllList(Pageable pageable);

    BmDto managerDetail(String username);

    MessageResult updateDetail(BmDto dto);

    void memberDelete(String idList);

     BmDto getManager(Long id);


}
