package com.zerobase.luffy.member.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {


    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"내부 서버오류가 있습니다."), //500
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"잘못된 요청입니다."), //400
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"인증되지 않은 회원입니다."), //401
    FORBIDDEN_ERROR(HttpStatus.FORBIDDEN,"요청권한이 없습니다."),//403
    PAGE_NOT_FOUND(HttpStatus.NOT_FOUND,"요청하신 페이지를 찾을수 없습니다."); //404


    private final HttpStatus status;

    private final String description;
}


