package com.gabrielle.delivery.dto;

import com.gabrielle.delivery.authentication.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role, String email) {
} 
