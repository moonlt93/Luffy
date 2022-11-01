package com.zerobase.luffy.member.admin.service.Impl;

import com.zerobase.luffy.member.admin.service.AdminService;
import com.zerobase.luffy.member.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {




    private final MemberRepository memberRepository;


    @Override
    public void memberDelete(String idList) {
        if(idList != null && idList.length()>0){
            String [] ids = idList.split(",");
            for (String x: ids
                 ) {
                long id= 0L;
                try{
                    id= Long.parseLong(x);
                }catch (Exception e){

                }
                if(id>0){
                    memberRepository.deleteById(id);
                }

            }
        }

    }

    }


