package com.gabrielle.delivery.cookies.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gabrielle.delivery.authentication.user.UserRole;
import com.gabrielle.delivery.cookies.service.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginIntercept implements HandlerInterceptor {

    private final CookieService cookieService;

    public LoginIntercept(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    private static final UserRole REQUIRED_ROLE_ADMIN = UserRole.ADMIN;
    private static final UserRole REQUIRED_ROLE_USER = UserRole.USER;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String roleValue = cookieService.getCookies(request, "role");

        if (roleValue != null) {
            try {
                UserRole userRole = UserRole.valueOf(roleValue.toUpperCase());
                if (userRole == REQUIRED_ROLE_ADMIN || userRole == REQUIRED_ROLE_USER) {
                    return true;
                } else {
                    System.err.println("Unidentified user type: " + roleValue);
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error parsing role: " + e);
            }
        }
        response.sendRedirect("/login");
        return false;
    }
}