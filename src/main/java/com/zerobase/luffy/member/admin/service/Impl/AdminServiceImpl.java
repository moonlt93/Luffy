package com.zerobase.luffy.member.admin.service.Impl;

import com.zerobase.luffy.main.entity.Coupon;
import com.zerobase.luffy.main.repository.CouponRepository;
import com.zerobase.luffy.member.admin.Dto.MemberInput;
import com.zerobase.luffy.member.admin.service.AdminService;
import com.zerobase.luffy.member.user.entity.Member;
import com.zerobase.luffy.member.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;


    @Override
    public void memberDelete(String idList) {
        log.info("id 리스트 확인 "+idList);
        if(idList != null && idList.length()>0){
            String [] ids = idList.split(",");
            for (String x: ids
                 ) {
                long id= 0L;
                try{
                     id = Long.parseLong(x);

                      List<Coupon> optionalCoupon=  couponRepository.findByMember_id(id);

                      if(!optionalCoupon.isEmpty()) {

                          couponRepository.deleteAll(optionalCoupon);
                          log.info("쿠폰 삭제");
                      }
                    memberRepository.deleteIds(id);
                    log.info("회원이 삭제되었습니다.");
                }catch (Exception e){
                 log.error("회원이 삭제되지 않았습니다.");
                }
            }
        }

    }


    @Override
    public void memberUpdate(MemberInput dto) {

        Optional<Member> optionalMember = Optional.ofNullable(memberRepository.findById(dto.getId())
                .orElseThrow(() -> new NullPointerException("id에 해당하는 회원이 없습니다.")));

        if(optionalMember.isPresent()){

            Member member = optionalMember.get();

            member.setId(dto.getId());
            member.setMemberStatus(dto.getMemberStatus());
            member.setROLE(dto.getROLE());

            memberRepository.save(member);
            log.info("member update success");
        }
        log.error("member update fail");


    }
}


