package com.gabrielle.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, Filter securityFilter) throws Exception {
         return httpSecurity
                 .csrf(csrf -> csrf.disable())
                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                     .authorizeHttpRequests(authorize -> authorize
                     .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                     .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                     .requestMatchers(HttpMethod.POST, "/email").permitAll()
                     .requestMatchers(HttpMethod.GET, "/admin/home").hasAnyRole("ADMIN", "USER")
                     .requestMatchers(HttpMethod.POST, "/admin/add").hasRole("ADMIN")
                     .requestMatchers(HttpMethod.DELETE, "/admin/{id}").hasRole("ADMIN")
                     .requestMatchers(HttpMethod.PATCH, "/admin/{id}").hasRole("ADMIN")
                     .requestMatchers(HttpMethod.POST, "/payment/number-card").hasRole("USER")
                     .requestMatchers(HttpMethod.POST, "/payment/card-flag").hasRole("USER")
                     .requestMatchers(HttpMethod.POST, "/api.upload").hasRole("ADMIN")
                     .requestMatchers( HttpMethod.GET, "/homeAdmin").hasRole("ADMIN")
                     .requestMatchers( HttpMethod.GET,"/", "/cadastrar", "/login").permitAll()                 
                     .requestMatchers( "/css/**", "/js/**", "/images/**").permitAll()
                     .anyRequest().authenticated()
                 )
                 .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                 .build();
        
    }
   
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
