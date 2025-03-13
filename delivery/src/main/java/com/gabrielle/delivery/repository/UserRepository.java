package com.gabrielle.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.gabrielle.delivery.authentication.user.User;

public interface UserRepository extends JpaRepository<User, String>{
    UserDetails findByLogin(String login);
}