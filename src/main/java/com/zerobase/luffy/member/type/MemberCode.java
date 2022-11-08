package com.zerobase.luffy.member.type;

import com.zerobase.luffy.member.user.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
public enum MemberCode {
    ING("사용중"),
    UNREGISTERED("등록이안된 사용자");

   private String description;

   MemberCode(String description){
       this.description=description;
   }

   public static MemberCode find(String description){
       return Arrays.stream(values())
               .filter(memberCode -> memberCode.description.equals(description))
               .findAny().orElse(UNREGISTERED);
   }

}
