package com.zerobase.luffy.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.luffy.Oauth.Provider.kakao.KakaoAccToken;
import com.zerobase.luffy.auth.PrincipalDetails;
import io.lettuce.core.ScriptOutputType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {



    @GetMapping({"/", " "})
    public String GetMain() {


        log.info("mainController" );


        return "index";
    }

    @GetMapping("/test/login")
    @ResponseBody
    public String testLogin(Authentication authentication,@AuthenticationPrincipal UserDetails userDetails){
        System.out.println("// test // login======================");
        PrincipalDetails principalDetails =(PrincipalDetails) authentication.getPrincipal();
        System.out.println("[하하]:"+ principalDetails.getUsername());
        System.out.println("[User details]"+userDetails.getUsername());
        return "세션 정보 확인";
    }


    @GetMapping("/auth/kakao/callback")
    @ResponseBody
    public String kakaoCallback(String code, HttpServletResponse response){

        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "0010f7f67c36e9cca1a46fc4bb2bae4d");
        params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        params.add("code", code);

        // 요청하기 위해 헤더(Header)와 데이터(Body)를 합친다.
        // kakaoTokenRequest는 데이터(Body)와 헤더(Header)를 Entity가 된다.
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params,headers);

        ResponseEntity<String> res =rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objmapper = new ObjectMapper();
        KakaoAccToken token = null;
        try{
            token = objmapper.readValue(res.getBody(), KakaoAccToken.class);
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        log.info("[카카오 엑세스 토큰]: "+ token);

        final String bear ="Bearer ";

        RestTemplate Authorization = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        assert token != null;
        headers2.add("Authorization",bear+token.getAccess_token());
        headers2.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        // 요청하기 위해 헤더(Header)와 데이터(Body)를 합친다.
        // kakaoTokenRequest는 데이터(Body)와 헤더(Header)를 Entity가 된다.
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest2 = new HttpEntity<>(headers2);

        ResponseEntity<String> resAuthorization =Authorization.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoTokenRequest2,
                String.class
        );
        return resAuthorization.getBody();



    }


}
