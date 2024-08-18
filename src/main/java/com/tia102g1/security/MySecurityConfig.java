package com.tia102g1.security;

import com.tia102g1.socialmember.service.MyOAuth2UserService;
import com.tia102g1.socialmember.service.MyOidcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    // 讓 Spring Security 讀取會員資料
    @Autowired
    MyUserDetailsService myUserDetailsService;

    // 以下兩個是讓 Spring Security 讀取社交登入的會員資料
    @Autowired
    private MyOAuth2UserService myOAuth2UserService;
    @Autowired
    private MyOidcUserService myOidcUserService;

    // 客製化的 login 失敗處理器
    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    // 設定密碼的加密機制為 BCrypt 方式
    public PasswordEncoder passwordEncoder() {
        //測試環境密碼不加密
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 關閉 CSRF 保護
                .authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/frontendapp/**", "/plungins/**").permitAll()
                .mvcMatchers("/index", "/register/**", "/login/**").permitAll()
                .mvcMatchers("/member/{memberId}/account/**").hasRole("MEMBER")
                .mvcMatchers("/favProduct").hasRole("MEMBER")
                .mvcMatchers("/admin", "/admin/index","").access("!hasRole('MEMBER')")
//                .mvcMatchers("/member/mainPageMember").hasAnyRole("STAFF","ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login") // 指定自定義的登入頁面
                .permitAll()
                .loginProcessingUrl("/login") // 登入表單提交的 URL
                .successHandler(customAuthenticationSuccessHandler)
//                .defaultSuccessUrl("/index", true) // 登入成功後的響應的 URL
                .failureHandler(customAuthenticationFailureHandler)

                .and()
                .rememberMe()
                .userDetailsService(myUserDetailsService)
                .and()
//                //OAuth2.0授權認證方法
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/index", false)
                .userInfoEndpoint()
                .userService(myOAuth2UserService)
                .oidcUserService(myOidcUserService);


    }
}
