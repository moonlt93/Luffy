package com.zerobase.luffy.configuration;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;




@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfiguration  {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .antMatchers("/member/my**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN')OR hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/member/login").loginProcessingUrl("/member/login").defaultSuccessUrl("/")
                .successHandler(getSuccessHandler())
                .failureHandler(getFailureHandler())
                .permitAll()
                .and().logout().logoutRequestMatcher(
                        new AntPathRequestMatcher("/member/logout")
                ).logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and().headers().frameOptions().sameOrigin();


    return http.build();
    }




    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Bean
    UserLoginSuccessHandler getSuccessHandler() {

        return new UserLoginSuccessHandler();
    }



    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring()
                .mvcMatchers("**/favicon.ico","/cdn**","/maxcdn**","code.jquery.com/jquery**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }



}
