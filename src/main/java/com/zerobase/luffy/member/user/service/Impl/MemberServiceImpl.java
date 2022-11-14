package com.zerobase.luffy.member.user.service.Impl;

import com.zerobase.luffy.main.entity.Coupon;
import com.zerobase.luffy.member.user.dto.MemberDto;
import com.zerobase.luffy.Util.passUtil;
import com.zerobase.luffy.member.user.entity.Member;
import com.zerobase.luffy.member.user.model.MessageResult;
import com.zerobase.luffy.member.user.repository.MemberRepository;
import com.zerobase.luffy.member.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.zerobase.luffy.member.type.MemberCode.ING;
import static com.zerobase.luffy.member.type.MemberCode.UNREGISTERED;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    public boolean register(MemberDto dto) {

        Optional<Member> optionalUsername = memberRepository.findByUsername(dto.getUserName());

        if(optionalUsername.isPresent()){
            return false;
        }

        String encPassword = passUtil.encPassword(dto.getPassword());


        Member member = Member.builder()
                .username(dto.getUserName())
                .password(encPassword)
                .phone(dto.getPhone())
                .registration(dto.getRegistration())
                .ip(dto.getIp())
                .regDt(LocalDateTime.now())
                .ROLE("ROLE_USER")
                .upDt(LocalDateTime.now())
                .memberStatus(ING)
                .email(dto.getEmail())
                .name(dto.getName())
                .coupons(new ArrayList<>())
                .build();


        for (int i = 1; i <=3 ; i++) {
            Coupon coupons = Coupon.builder()
                    .title(i*10+"% 할인 쿠폰")
                    .rate(i*10)
                    .member(member)
                    .build();

            member.getCoupons().add(coupons);
        }


        memberRepository.save(member);


        return true;
    }


    @Override
    public MemberDto memberDetails(String username) {

        Optional<Member> optionMember= memberRepository.findByUsername(username);
        if(optionMember.isEmpty()){
            return null;
        }
        Member member = optionMember.get();

        return MemberDto.EntityBuild(member);


    }


    @Override
    public MessageResult updateDetail(MemberDto dto) {

        Long id= dto.getId();
        System.out.println(id);

        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isEmpty()){
            return new MessageResult(false,"회원정보가 존재하지 않습니다.");
        }
        Member member = optionalMember.get();
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());
        member.setUpDt(LocalDateTime.now());
        member.setRegistration(dto.getRegistration());
        memberRepository.save(member);

        return new MessageResult(true);
    }


    @Override
    public MessageResult withDraw(Long userId, String password) {


        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(optionalMember.isEmpty()){
            return new MessageResult(false,"회원정보가 존재하지 않습니다.");

        }

        Member member = optionalMember.get();
        if(!passUtil.equals(password,member.getPassword())){
            return new MessageResult(false,"비밀번호가 일치하지 않습니다.");
        }

        else{


        member.setUsername("삭제회원");
        member.setEmail("");
        member.setName("");
        member.setEndDt(LocalDateTime.now());
        member.setIp("");
        member.setPassword("");
        member.setROLE("");
        member.setRegistration("");
        member.setMemberStatus(UNREGISTERED);
        memberRepository.save(member);

        }


        return new MessageResult();
    }

    public MemberDto getMember(Long userId){
        MemberDto member = MemberDto.EntityBuild(memberRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("USER_NOT_FOUND")));

            return member;
    }

    @Override
    public List<MemberDto> memberList(MemberDto dto) {
        List<Member> list = memberRepository.findAll();

       if(!CollectionUtils.isEmpty(list)){
           log.info("리스트 있음");
       }

        return list.stream()
                .map(MemberDto :: EntityBuild)
                .collect(Collectors.toList());
    }
}


/*@Override
    public List<MemberDto> getUserListByUsername(String username) {

        List<Member> list = memberRepository.findUserListByUsername(username);

        if(list.isEmpty()){
            return (List<MemberDto>) new NullPointerException("리스트가 없습니다.");
        }

        return list.stream()
                .map(MemberDto::EntityBuild)
                .collect(Collectors.toList());

    }*/