package com.zerobase.luffy.member.user.service;


import com.zerobase.luffy.member.user.dto.MemberDto;
import com.zerobase.luffy.member.user.model.MessageResult;

import java.util.List;

public interface MemberService {

     boolean register(MemberDto dto);


    MemberDto memberDetails(String username);

    MessageResult updateDetail(MemberDto dto);

    MessageResult withDraw(Long id, String password);

    List<MemberDto> memberList(MemberDto dto);
}
