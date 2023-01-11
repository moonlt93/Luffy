package com.zerobase.luffy.configuration;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
@Aspect
public class BindingAdvice {


    @Before("execution(* com.zerobase.luffy.member.user.controller.MemberController.*(..))")
    public void test() {

        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();


        System.out.println("해당 Method 타입:"+ request.getMethod());
        System.out.println("해당 주소 :" + request.getRequestURI());
        System.out.println("전처리 로그");
    }





    @Around("execution(public * com.zerobase.luffy.member.user.controller.MemberController.*(..))")
    public Object validationCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String method = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();

        System.out.println("type: " + type);
        System.out.println("method = " + method);

        for (Object arg : args
        ) {
            if (arg instanceof BindingAdvice) {

                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                        log.warn(type+"."+method+"() => 필드 : "+error.getField() + ", 메시지 : "+error.getDefaultMessage());
                    }

                    return new Exception("ERRORS");
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }



}

