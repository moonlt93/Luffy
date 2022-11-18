package com.zerobase.luffy.main.service.Impl;

import com.zerobase.luffy.main.dto.WishDto;
import com.zerobase.luffy.main.entity.Wish;
import com.zerobase.luffy.main.repository.WishRepository;
import com.zerobase.luffy.main.service.WishService;
import com.zerobase.luffy.member.admin.entity.Photoes;
import com.zerobase.luffy.member.user.entity.Member;
import com.zerobase.luffy.member.user.entity.OrderItem;
import com.zerobase.luffy.member.user.entity.OrderProduct;
import com.zerobase.luffy.member.user.repository.MemberRepository;
import com.zerobase.luffy.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WishServiceImpl implements WishService {


    private final WishRepository wishRepository;
    private final MemberRepository memberRepository;

    @Override
    public ResponseMessage createWish(WishDto dto, String username) {

        Optional<Member> optionalMember = memberRepository.findByUsername(username);


        if (optionalMember.isPresent()) {
            Member member = new Member();
            member.setId(optionalMember.get().getId());
            member.setWishes(new ArrayList<>());


            Wish wish = Wish.builder()
                    .wishId(dto.getWishId())
                    .fileUrl(dto.getFileUrl())
                    .productName(dto.getProductName())
                    .productUrl(dto.getProductUrl())
                    .member(member)
                    .build();

            member.addWishes(wish);

            wishRepository.save(wish);
            return ResponseMessage.success;
        }

        return ResponseMessage.fail;
    }

    @Override
    public List<Wish> getWishes(Long id) {

        List<Wish> wishes = wishRepository.findByMember_Id(id);

        return wishes;

    }

    @Override
    public void deleteWishes(String id) {


        String idList = id;

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");

            for (String x : ids) {
                long idss = 0L;
                try {

                    idss = Long.parseLong(x);

                    wishRepository.deleteId(idss);


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
