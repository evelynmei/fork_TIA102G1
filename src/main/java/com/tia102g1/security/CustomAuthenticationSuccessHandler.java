package com.tia102g1.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 取得使用者的所有權限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // 根據權限導向不同頁面
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_STAFF")) {
                response.sendRedirect("/admin");
                return;
            } else if (authority.getAuthority().equals("ROLE_MEMBER")) {
                response.sendRedirect("/index");
                return;
            }
        }

        // 如果沒有特定角色，導向預設首頁
        response.sendRedirect("/index");
    }
}
