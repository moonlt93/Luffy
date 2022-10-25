package com.zerobase.luffy.Exception;


import com.zerobase.luffy.member.type.ErrorCode;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberException extends RuntimeException {


private ErrorCode errorCode;
private String errorMessage;

public MemberException(ErrorCode errorCode){
    this.errorCode =errorCode;
    this.errorMessage=errorCode.getDescription();
}


}
