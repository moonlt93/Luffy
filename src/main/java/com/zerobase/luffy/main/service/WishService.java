package com.zerobase.luffy.main.service;

import com.zerobase.luffy.main.dto.WishDto;
import com.zerobase.luffy.main.entity.Wish;
import com.zerobase.luffy.response.ResponseMessage;

import java.util.List;

public interface WishService {
   public ResponseMessage createWish(WishDto dto, String username);


   List<Wish> getWishes(Long id);

    void deleteWishes(String id);
}
