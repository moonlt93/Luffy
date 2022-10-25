package com.zerobase.luffy.member.common.service;


import com.zerobase.luffy.member.common.dto.MemberDto;
import com.zerobase.luffy.member.common.model.MessageResult;

public interface MemberService {

     boolean register(MemberDto dto);



    MemberDto memberDetails(String username);

    MessageResult updateDetail(MemberDto dto);

    MessageResult withDraw(Long id, String password);
}
