package com.zerobase.luffy.Util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//차후 수정

@Slf4j
public class loginInterceptor implements HandlerInterceptor {
    UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ("**/login".equals(urlPathHelper.getLookupPathForRequest(request)) && request.authenticate(response)){

            String encodedRedirectURL = response.encodeRedirectURL(
                    request.getContextPath()+"/");
            response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
            response.setHeader("Location",encodedRedirectURL);
            return false;

        } else{
            return true;
        }


    }
}
