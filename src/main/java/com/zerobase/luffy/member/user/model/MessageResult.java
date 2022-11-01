package com.zerobase.luffy.member.user.model;

import lombok.Data;

@Data
public class MessageResult {

    boolean result;
    String message;

   public MessageResult(boolean b, String m){
       this.result=b;
       this.message=m;
   }

   public MessageResult(){
       this.result=true;
   }
   public MessageResult(boolean b){
       this.result = b;
   }


}
