package com.gabrielle.delivery.cookies.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gabrielle.delivery.authentication.user.UserRole;
import com.gabrielle.delivery.cookies.service.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginIntercept implements HandlerInterceptor {

    private static final UserRole REQUIRED_ROLE = UserRole.ADMIN;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String roleValue = CookieService.getCookies(request, "role");

        if (roleValue != null) {
            try {
                UserRole userRole = UserRole.valueOf(roleValue.toUpperCase());
                if (userRole == REQUIRED_ROLE) {
                    return true;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error:" + e);
            }
        }

        response.sendRedirect("/login"); 
        return false;
    }
}
